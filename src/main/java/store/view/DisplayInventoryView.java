package store.view;

import store.domain.Product;
import store.domain.Promotion;
import store.domain.Storage;

import java.text.NumberFormat;
import java.util.Map;

import static store.view.DisplayInventoryView.Inventory.*;

public class DisplayInventoryView {
    private Storage storage;
    NumberFormat numberFormat = NumberFormat.getInstance();

    enum Inventory {
        WELCOME("안녕하세요. W편의점입니다."),
        PRONOUNCE_MENU("현재 보유하고 있는 상품입니다."),
        NON_PROMOTION_PRODUCT_STORAGE_INFO("- %s %s원 %s"),
        PROMOTION_PRODUCT_STORAGE_INFO("- %s %s원 %s %s"),
        DEPLETED_PRODUCT("재고 없음");
        private String message;

        Inventory(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public String formatMessage(Object... args) {
            return String.format(message, args);
        }
    }

    public DisplayInventoryView(Storage storage) {
        this.storage = storage;
    }

    public void displayInventoryView() {
        printWelcomeMessage();
        printPronounceMenu();
    }

    private void printWelcomeMessage() {
        System.out.println(WELCOME.getMessage());
    }

    private void printPronounceMenu() {
        Map<String, Product> products = storage.getProducts();
        System.out.println(PRONOUNCE_MENU.getMessage());
        System.out.println();
        for (String key : products.keySet()) {
            Product product = products.get(key);
            printBothOfNonPromotionAndPromotion(product);
        }
    }

    private void printBothOfNonPromotionAndPromotion(Product product) {

        String price = numberFormat.format(product.getPrice());
        if (product.hasPromotion()) {
            Promotion promotion = product.getPromotion();
            String quantity = formatQuantity(product.getPromotionQuantity());
            System.out.println(PROMOTION_PRODUCT_STORAGE_INFO.formatMessage(product.getProductName(), price, quantity, promotion.getPromotionName()));
        }
        if (product.getNonPromotionQuantity() != null) {
            String quantity = formatQuantity(product.getNonPromotionQuantity());
            System.out.println(NON_PROMOTION_PRODUCT_STORAGE_INFO.formatMessage(product.getProductName(), price, quantity));
        }
    }

    private String formatQuantity(int quantity) {
        if (quantity == 0) {
            return DEPLETED_PRODUCT.getMessage();
        }
        return quantity + "개";
    }
}
