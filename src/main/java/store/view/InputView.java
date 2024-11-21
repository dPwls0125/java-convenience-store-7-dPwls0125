package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.domain.*;
import store.dto.AvailableStockDto;
import store.exception.PurchaseException;

import store.service.PromotionService;
import store.service.PurchaseService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static store.constant.PurchaseErrorMessage.*;
import static store.view.InputView.Menual.*;

// todo: 클래스 이름 더 직관적으로 변경
public class InputView {
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

    public InputView(Storage storage){
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

    public void applyPromotion(Customer customer){

        for (Purchase p : customer.getPurchases()){
            Product product = p.getProduct();
            // 프로모션이 없는 경우
            if (p.getProduct().getPromotion() == null) {
                AvailableStockDto dto = promotionService.getAvailableStorage(product.getProductName(),p.getQuantity());
                promotionService.updateStorage(dto,product);
                // TODO bills 추가
                continue;
            }
            // 프로모션이 있는 경우 적용
            askPromotionAndApplyExtra(p);
            AvailableStockDto dto = promotionService.getAvailableStorage(product.getProductName(), p.getQuantity());
            if (dto.getAvailableStockForPromotion() < p.getQuantity()) {
                if (inputYesOrNO(GUILD_ABOUT_PROMOTION2.formatMessage(p.getProductName(),dto.getAvailableStockForNonPromotion()))){
                    dto.setAvailableStockForPromotion(dto.getAvailableStockForPromotion());
                    promotionService.updateStorage(dto,product);
                    // Todo : bills 추가
                    return;
                }
                // todo: N: 정가로 결제해야하는 수량만큼 제외한 후 결제를 진행한다.

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



    private boolean inputYesOrNO(String ask){
        while(true){
            try{
                System.out.println(ask);
                String input = Console.readLine();
                if(input.equals("Y") ) return true;
                if(input.equals("N")) return false;
                else throw PurchaseException.from(PLEASE_ANSWER_YN);
            }catch(PurchaseException e){
                System.out.println(e.getMessage());
            }

        }
    }


//    public void displayPurchaseViewAndInput(){
//        System.out.println(Menual.GUID_THE_WAY_TO_PURCHASE.getMessage());
//        getInputAboutPromotion();
//        System.out.println(Menual.GUID_ABOUT_MEMBERSHIP.getMessage());
//        getInputAboutMembership();
//
//    }
//
//    private void getInputAboutPromotion() {
//        while(true){
//            try{
//                String input = Console.readLine();
//                String[] purchaseInput = input.split(",");
//                enrollPurchase(purchaseInput);
//                break;
//
//            }catch (PurchaseException e){
//                System.out.println(e.getMessage());
//            }
//        }
//    }
//
//    private void getInputAboutMembership(){
//        while(true){
//            try{
//                branchForYesOrNO();
//                break;
//            }catch (PurchaseException e){
//                System.out.println(e.getMessage());
//            }
//        }
//    }
//
//    private void branchForYesOrNO() {
//        String input = Console.readLine();
//        if(input.equals("Y")){
//            customer.setApplicateMembership(true);
//            return;
//        }
//        if(input.equals("N")){
//            customer.setApplicateMembership(false);
//            return;
//        }
//        throw PurchaseException.from(PELASE_ANSWER_YN);
//    }
//
//    private void enrollPurchase(String[] purchaseInput) {
//        for(String purchaseItem : purchaseInput){
//            String line[] = purchaseItem.split("-");
//            String productName = line[0].substring(1, line[0].length());
//            String quantity = line[1].substring(0, line[1].length()-1);
//            int extractQuantity = Integer.parseInt(quantity);
//            checkExtraBonus(productName, extractQuantity);
//        }
//    }
//
//    private void checkExtraBonus(String productName, int extractQuantity) {
//        Product product = storage.getProducts().get(productName);
//        Purchase purchase = Purchase.of(product, extractQuantity);
//        if(product.hasPromotion()) {
//            Promotion promotion = product.getPromotion();
//            int bonusQuantity = priceCalculator.calculatePromotionBonus(promotion, extractQuantity);
//            branchFormPromotionAdoptation(purchase, bonusQuantity,extractQuantity);
//        }
//    }
//
//    private void branchFormPromotionAdoptation(Purchase purchase, int bonusQuantity, int quantity) {
//        if (bonusQuantity > 0) {
//            System.out.printf(Menual.GUID_ABOUT_PROMOTION1.forametMessage(purchase.getProductName(), bonusQuantity));
//            askForPromotionAndSave(purchase.getProductName(), quantity);
//        }
//    }
//
//    private void askForPromotionAndSave(String productName, int quantity){
//        String input = Console.readLine();
//        Purchase purchase = Purchase.of(storage.getProducts().get(productName),quantity);
//        customer.addPurchase(purchase);
//        if(input.equals("N")){
//            customer.addBillPerProductsForNonPromotion(purchase);
//            return;
//        }
//        if(input.equals("Y")){
//            customer.addBillPerProductsForPromotion(purchase);
//            return;
//        }
//        throw PurchaseException.from(PELASE_ANSWER_YN);
//    }
//
//    private String extractProductName(Matcher matcher) {
//        if (matcher.find()) {
//            String productName = matcher.group(1);
//            return productName;
//        }
//        throw PurchaseException.from(INVALID_PRODUCT_NAME);
//    }
//
//    private String extractQuantity(Matcher matcher){
//        if (matcher.find()) {
//            String quantity = matcher.group(2);
//            return quantity;
//        }
//        throw PurchaseException.from(INVALID_PRODUCT_NAME);
//    }

}
