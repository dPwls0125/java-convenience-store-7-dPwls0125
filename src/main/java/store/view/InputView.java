package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.domain.*;
import store.exception.PurchaseException;
import store.service.ProductPriceCalculator;

import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static store.constant.PurchaseErrorMessage.*;

public class InputView {
    private Customer customer; // 고객 정보를 가지고 있는 객체
    private Storage storage; // 상품 목록을 가지고 있는 객체
    private ProductPriceCalculator priceCalculator;
    private Promotions promotions; // 프로모션 목록을 가지고 있는 객체
    enum Menual{
        GUID_THE_WAY_TO_PURCHASE("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])"),
        GUID_ABOUT_MEMBERSHIP("멤버십 할인을 받으시겠습니까? (Y/N)"),
        GUID_ABOUT_PROMOTION1("현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)"),
        GUILD_ABOUT_PROMOTION2("현재 콜라 %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)");
        private String message;

        public String getMessage() {
            return message;
        }
        Menual(String message) {
            this.message = message;
        }
        public String forametMessage(Object... args){ // 가변인자
            return String.format(message, args);
        }
    }

    public InputView(Storage storage, Customer customer){
        this.storage = storage;
        this.priceCalculator = new ProductPriceCalculator();
        this.customer = customer;
    }
    public void displayPurchaseViewAndInput(){
        System.out.println(Menual.GUID_THE_WAY_TO_PURCHASE.getMessage());
        getInputAboutPromotion();
        System.out.println(Menual.GUID_ABOUT_MEMBERSHIP.getMessage());
        getInputAboutMembership();

    }

    private void getInputAboutPromotion() {
        while(true){
            try{
                String input = Console.readLine();
                String[] purchaseInput = input.split(",");
                enrollPurchase(purchaseInput);
                break;

            }catch (PurchaseException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private void getInputAboutMembership(){
        while(true){
            try{
                branchForYesOrNO();
                break;
            }catch (PurchaseException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private void branchForYesOrNO() {
        String input = Console.readLine();
        if(input.equals("Y")){
            customer.setApplicateMembership(true);
            return;
        }
        if(input.equals("N")){
            customer.setApplicateMembership(false);
            return;
        }
        throw PurchaseException.from(PELASE_ANSWER_YN);
    }

    private void enrollPurchase(String[] purchaseInput) {
        for(String purchaseItem : purchaseInput){
            String line[] = purchaseItem.split("-");
            String productName = line[0].substring(1, line[0].length());
            String quantity = line[1].substring(0, line[1].length()-1);
            int extractQuantity = Integer.parseInt(quantity);
            checkExtraBonus(productName, extractQuantity);
        }
    }

    private void checkExtraBonus(String productName, int extractQuantity) {
        Product product = storage.getProducts().get(productName);
        Purchase purchase = Purchase.of(product, extractQuantity);
        if(product.hasPromotion()) {
            Promotion promotion = product.getPromotion();
            int bonusQuantity = priceCalculator.calculatePromotionBonus(promotion, extractQuantity);
            branchFormPromotionAdoptation(purchase, bonusQuantity,extractQuantity);
        }
    }

    private void branchFormPromotionAdoptation(Purchase purchase, int bonusQuantity, int quantity) {
        if (bonusQuantity > 0) {
            System.out.printf(Menual.GUID_ABOUT_PROMOTION1.forametMessage(purchase.getProductName(), bonusQuantity));
            askForPromotionAndSave(purchase.getProductName(), quantity);
        }
    }

    private void askForPromotionAndSave(String productName, int quantity){
        String input = Console.readLine();
        Purchase purchase = Purchase.of(storage.getProducts().get(productName),quantity);
        customer.addPurchase(purchase);
        if(input.equals("N")){
            customer.addBillPerProductsForNonPromotion(purchase);
            return;
        }
        if(input.equals("Y")){
            customer.addBillPerProductsForPromotion(purchase);
            return;
        }
        throw PurchaseException.from(PELASE_ANSWER_YN);
    }

    private String extractProductName(Matcher matcher) {
        if (matcher.find()) {
            String productName = matcher.group(1);
            return productName;
        }
        throw PurchaseException.from(INVALID_PRODUCT_NAME);
    }

    private String extractQuantity(Matcher matcher){
        if (matcher.find()) {
            String quantity = matcher.group(2);
            return quantity;
        }
        throw PurchaseException.from(INVALID_PRODUCT_NAME);
    }

}
