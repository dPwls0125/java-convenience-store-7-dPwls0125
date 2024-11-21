//package store.view;
//
//import store.domain.BillPerProduct;
//import store.domain.Customer;
//import store.domain.Product;
//import store.domain.Purchase;
//import store.dto.TotalBillDto;
//import store.service.ProductPriceCalculator;
//import store.service.ProductsPriceCalculator;
//
//import java.text.NumberFormat;
//import java.util.List;
//
//import static store.view.DisplayReceiptView.Receipt.*;
//
//public class DisplayReceiptView {
//    private Customer customer;
//    NumberFormat numberFormat = NumberFormat.getInstance();
//    enum Receipt {
//        START_RECEIPT_DIVIDER("==============W 편의점================"),
//        COLOUMN("상품명\t\t수량\t금액"),
//        COLUMN_VALUE("%s\t\t%d\t%s"),
//        GIVE_AWAY_DIVIDER("=============증\t정==============="),
//        GIVEAWAY_COLUMN("%s\t\t%d"),
//        END_RECEIPT_DIVIDER("===================================="),
//        TOTAL_PRICE("총구매액\t\t%d\t%s"),
//        DISCOUNT_WITH_PROMOTION("행사할인\t\t\t%s"),
//        DISCOUNT_WITH_MEMBERSHIP("멤버십할인\t\t\t%s"),
//        RESULT_PRICE("내실돈\t\t\t %s");
//        private final String message;
//        Receipt(String message) {
//            this.message = message;
//        }
//        public String format(Object... args) {
//            return String.format(this.message, args);
//        }
//
//    }
//
//    public DisplayReceiptView(Customer customer) {
//        this.customer = customer;
//
//    }
//
//    public void disPlayReceipt() {
//        List<BillPerProduct> billPerProducts = customer.getBillPerProducts();
//        printPurchasedProductInfos(billPerProducts);
//        printGiveAway(billPerProducts);
//        printResult(billPerProducts);
//    }
//
//    private void printPurchasedProductInfos(List<BillPerProduct> bills) {
//        System.out.println(START_RECEIPT_DIVIDER.message);
//        System.out.println(COLOUMN.message);
//        for (BillPerProduct purchase : bills) {
//            Product product = purchase.getPurchase().getProduct();
//            String price = numberFormat.format(purchase.getPurchase().getPrice());
//            int quantity = purchase.getPurchase().getQuantity();
//            System.out.println(COLUMN_VALUE.format(product.getProductName(),quantity, price));
//        }
//    }
//
//    private void printGiveAway(List<BillPerProduct> billPerProducts) {
//        System.out.println(GIVE_AWAY_DIVIDER.message);
//        for (BillPerProduct bill : billPerProducts) {
//            checkAndPrintGiveAwayQuantityPerProduct(bill, bill.getPurchase());
//        }
//    }
//
//    private void checkAndPrintGiveAwayQuantityPerProduct(BillPerProduct bill, Purchase purchase) {
//        if(bill.getGiveAwayQuantity() > 0) {
//            System.out.println(GIVEAWAY_COLUMN.format(purchase.getProductName(), bill.getGiveAwayQuantity()));
//        }
//    }
//    private void printResult(List<BillPerProduct> billPerProducts) {
//        TotalBillDto totalBillDto = productsPriceCalculator.getTotalBillDTo(billPerProducts, customer.isApplicateMembership());
//        System.out.println(END_RECEIPT_DIVIDER.message);
//        System.out.println(TOTAL_PRICE.format(totalBillDto.getTotalQuantity(), numberFormat.format(totalBillDto.getTotalPrice())));
//        System.out.println(DISCOUNT_WITH_PROMOTION.format(numberFormat.format(totalBillDto.getTotalPromotionDiscount())));
//        System.out.println(DISCOUNT_WITH_MEMBERSHIP.format(numberFormat.format(totalBillDto.getMemberShipDiscount())));
//    }
//
//
//}
