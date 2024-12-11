package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.domain.*;
import store.dto.AvailableStockDto;
import store.dto.PurchaseStockDto;
import store.exception.PurchaseException;

import store.service.PromotionService;
import store.service.PurchaseService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static store.constant.PurchaseErrorMessage.*;
import static store.view.PurchaseRequestHandleView.Menual.*;

public class PurchaseRequestHandleView {
    PurchaseService purchaseService;
    PromotionService promotionService;
    enum Menual{
        GUID_THE_WAY_TO_PURCHASE("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])"),
        GUID_ABOUT_MEMBERSHIP("멤버십 할인을 받으시겠습니까? (Y/N)"),
        GUID_ABOUT_PROMOTION1("현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)"),
        GUILD_ABOUT_PROMOTION2("현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)"),
        ASK_ABOUT_MORE_PURCHASE("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)");
        private String message;

        public String getMessage() {
            return message;
        }
        Menual(String message) {
            this.message = message;
        }
        public String formatMessage(Object... args){ // 가변인자
            return String.format(message, args);
        }
    }

    public PurchaseRequestHandleView(Storage storage){
        this.purchaseService = new PurchaseService(storage);
        this.promotionService = new PromotionService(storage);
    }

    public boolean askAboutMorePurchase(){
        System.out.println(ASK_ABOUT_MORE_PURCHASE.getMessage());
        String input = Console.readLine();
        if(input.equals("N")) return false;
        if(input.equals("Y")) return true;
        throw PurchaseException.from(PLEASE_ANSWER_YN);
    }

// indent 충족 안됨
    public void inputPurchaseWantItemAndSave(Customer customer){
        // 원하는 상품 구매
        while (true) {
            try {
                parsePurchaseWantItem(customer);
            } catch (PurchaseException e){
                System.out.println(e.getMessage());
                continue;
            }
            break;
        }
    }

    private void parsePurchaseWantItem(Customer customer){

        System.out.println(GUID_THE_WAY_TO_PURCHASE.getMessage());
        String input = Console.readLine();
        Pattern pattern = Pattern.compile("\\[(.+?)-(\\d+)]");
        Matcher matcher = pattern.matcher(input);

        if(!matcher.find()) throw PurchaseException.from(PLEASE_ANSWER_RIGHT_WAY);

        do {
            String productName = matcher.group(1);
            Integer quantity = Integer.parseInt(matcher.group(2));
            purchaseService.addPurchase(customer,productName,quantity);
        } while(matcher.find());
    }

    // TODO : 아래 코드 중복을 리팩토링 해서 else 제거 하도록
    public void applyPromotion(Customer customer){

        for (Purchase p : customer.getPurchases()){
//            System.out.println("PurchaseRequestHandlerView-applayPromotion: "+p.getProductName());
            Product product = p.getProduct();
            // 프로모션이 없는 경우
            if (p.getProduct().getPromotion() == null) {
                AvailableStockDto availableStockDto = promotionService.getAvailableStorage(product.getProductName(),p.getQuantity());
                PurchaseStockDto purchaseStockDto = PurchaseStockDto.of(availableStockDto.getAvailableStockForNonPromotion(), availableStockDto.getAvailableStockForPromotion());
                promotionService.updateStorage(purchaseStockDto,product);
                BillPerProduct bill = new BillPerProduct(p,availableStockDto.getTotalAvailableStock(),0);
                customer.addBillForProducts(bill);
                continue;
            }
            // 프로모션이 있는 경우 적용
            askPromotionAndApplyExtra(p);
            AvailableStockDto dto = promotionService.getAvailableStorage(product.getProductName(), p.getQuantity());
            // 프로모션의 재고를 사용하더라도, 수량의 짝에 따라 promotion이 적용되지 않는 경우도 존재할 수 있음. 이에따라, promotion이 적용된 재고를 따로 계산
            int purchaseQuantityInPromotion = promotionService.calculateAppliedPromotionQuantity(product,dto.getAvailableStockForPromotion());
            int giveAwayQuantity = promotionService.calculateGiveAwayQuantity(product,purchaseQuantityInPromotion);

            // 사고자 하는양이 프로모션이 적용되는 수량보다 적은 경우
            if (purchaseQuantityInPromotion < p.getQuantity()) {
                int pruchaseQuantityCannotInpromotion =  p.getQuantity() - purchaseQuantityInPromotion;
                if (inputYesOrNO(GUILD_ABOUT_PROMOTION2.formatMessage(p.getProductName(),pruchaseQuantityCannotInpromotion))){
                    PurchaseStockDto purchaseStockDto = PurchaseStockDto.of(dto.getAvailableStockForNonPromotion(), dto.getAvailableStockForPromotion());
                    BillPerProduct bill = new BillPerProduct(p,dto.getTotalAvailableStock(),giveAwayQuantity);
                    promotionService.updateStorage(purchaseStockDto,product);
                    customer.addBillForProducts(bill);
                }
                else {
                    PurchaseStockDto purchaseStockDto = PurchaseStockDto.of(0,purchaseQuantityInPromotion);
                    BillPerProduct bill = new BillPerProduct(p,purchaseQuantityInPromotion,giveAwayQuantity);
                    promotionService.updateStorage(purchaseStockDto,product);
                    customer.addBillForProducts(bill);
                }
            }
            // 모든 수량을 프로모션 적용 할 수 있는 경우
            if (purchaseQuantityInPromotion >= p.getQuantity()){
                PurchaseStockDto purchaseStockDto = PurchaseStockDto.of(0,purchaseQuantityInPromotion);
                BillPerProduct bill = new BillPerProduct(p,purchaseQuantityInPromotion,giveAwayQuantity);
                promotionService.updateStorage(purchaseStockDto,product);
                customer.addBillForProducts(bill);
            }
        }
    }
    private void askPromotionAndApplyExtra(Purchase purchase){
        Product product = purchase.getProduct();
        int extraBonus = promotionService.getCalculatedAvailableBonusQuantity(product,purchase.getQuantity());

        // 추가 구매 가능 수량이 없는 경우
        if (extraBonus < 1) {
            return;
        }
        // 추가 구매 의사 묻기
        boolean isApplyExtra = inputYesOrNO(GUID_ABOUT_PROMOTION1.formatMessage(product.getProductName(),extraBonus));

        // 추가로 구매하지 않을 경우
        if (!isApplyExtra) {
            return;
        }
        // 구매 수량 증가
        purchase.setQuantity(purchase.getQuantity() + extraBonus);
    }

    private boolean inputYesOrNO(String ask) {

        while (true) {
            try{
                System.out.println(ask);
                String input = Console.readLine();
                if (input.equals("Y") ) return true;
                if (input.equals("N")) return false;
                throw PurchaseException.from(PLEASE_ANSWER_YN);
            } catch(PurchaseException e){
                System.out.println(e.getMessage());
            }

        }
    }

}
