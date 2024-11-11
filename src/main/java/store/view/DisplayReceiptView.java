package store.view;

import store.domain.Customer;
import store.domain.Product;
import store.domain.Purchase;

import java.text.NumberFormat;
import java.util.List;

import static store.view.DisplayReceiptView.Receipt.*;

public class DisplayReceiptView {
    private Customer customer;
    NumberFormat numberFormat = NumberFormat.getInstance();

    enum Receipt{
        START_RECEIPT_DIVIDER("==============W 편의점================"),
        GIVE_AWAY_DIVIDER("=============증\t정==============="),
        END_RECEIPT_DIVIDER("===================================="),
        COLOUMN("상품명\t\t수량\t금액"),
        COLUMN_VALUE("%s\t\t%d\t%s"),
        TOTAL_PRICE("총구매액\t\t%d\t%s"),
        DISCOUNT_WITH_PROMOTION("행사할인\t\t\t%s"),
        DISCOUNT_WITH_MEMBERSHIP("멤버십할인\t\t\t%s"),
        RESULT_PRICE("내실돈\t\t\t %s");
        private final String message;
        Receipt(String message){
            this.message = message;
        }
        public String format(Object... args) {
            return String.format(this.message, args);
        }

    }

    public DisplayReceiptView(Customer customer){
        this.customer = customer;

    }

    public void disPlayReceipt(){
        List<Purchase> products = customer.getPurchases();
        printPurchasedProductInfos(products);

    }

    private void printPurchasedProductInfos(List<Purchase>purchases){
        System.out.println(START_RECEIPT_DIVIDER);
        System.out.println(COLOUMN);
        for(Purchase purchase : purchases){
            Product product = purchase.getProduct();
            String totalPrice = numberFormat.format(product.getPrice());
            System.out.println(COLUMN_VALUE.format(product.getProductName(), purchase.getQuantity(),totalPrice));
        }
    }

    private void printGiveAway(){
        System.out.println(GIVE_AWAY_DIVIDER);


    }

    private void printResult(){

    }


}
