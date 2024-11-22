package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.domain.BillPerProduct;
import store.domain.Customer;
import store.domain.Product;
import store.domain.Purchase;
import store.dto.TotalBillDto;
import store.exception.PurchaseException;
import store.service.CalculateBillService;

import java.text.NumberFormat;
import java.util.List;

import static store.constant.PurchaseErrorMessage.PLEASE_ANSWER_YN;
import static store.view.DisplayReceiptView.Receipt.*;

public class DisplayReceiptView {
    private Customer customer;
    private CalculateBillService calculateBillService;
    NumberFormat numberFormat = NumberFormat.getInstance();
    enum Receipt {

        ASK_ABOUT_MEMBERSHIP("멤버십 할인을 받으시겠습니까? (Y/N)"),
        START_RECEIPT_DIVIDER("==============W 편의점================"),
        COLOUMN("상품명\t\t수량\t금액"),
        COLUMN_VALUE("%s\t\t%d\t%s"),
        GIVE_AWAY_DIVIDER("=============증\t정==============="),
        GIVEAWAY_COLUMN("%s\t\t%d"),
        END_RECEIPT_DIVIDER("===================================="),
        TOTAL_PRICE("총구매액\t\t%d\t%s"),
        DISCOUNT_WITH_PROMOTION("행사할인\t\t\t%s"),
        DISCOUNT_WITH_MEMBERSHIP("멤버십할인\t\t\t%s"),
        RESULT_PRICE("내실돈\t\t\t %s");
        private final String message;
        Receipt(String message) {
            this.message = message;
        }
        public String format(Object... args) {
            return String.format(this.message, args);
        }

        public String getMessage(){
            return message;
        }

    }

    public DisplayReceiptView(Customer customer) {
        this.customer = customer;
        this.calculateBillService = new CalculateBillService();
    }

    public void displayReceipt(){
        boolean isApplyMembership = askAboutMembershipApplying();
        TotalBillDto dto = calculateBillService.getTotalBill(customer,isApplyMembership);
        List<BillPerProduct> bills = customer.getBillPerProducts();
        printTotalPurchasedList(bills);
        printGiveAwayItemAndQuantity(bills);
        printTotalPurchasedAmount(dto);
        printTotalOnSaleAmount(dto);
        printMembershipSaleAmount(dto);
        printTheAmountCharged(dto);
        customer.getPurchases().clear();
        customer.getBillPerProducts().clear();
    }
    private boolean askAboutMembershipApplying() {
        return inputYesOrNO(ASK_ABOUT_MEMBERSHIP.getMessage());
    }

    private void printTotalPurchasedList(List<BillPerProduct> bills) {
        System.out.println(START_RECEIPT_DIVIDER.getMessage());
        System.out.println(COLOUMN.getMessage());
        for(BillPerProduct bill : bills){
            Purchase purchase = bill.getPurchase();
            Product product = purchase.getProduct();
            int price = product.getPrice() * purchase.getQuantity();
            System.out.println(COLUMN_VALUE.format(product.getProductName(),bill.getTotalQuantity(),Integer.toString(price)));
        }
    }

    private void printGiveAwayItemAndQuantity(List<BillPerProduct> bills) {
        System.out.println(GIVE_AWAY_DIVIDER.getMessage());
        for(BillPerProduct bill : bills){
            if(bill.getGiveAwayQuantity() > 0 ){
                System.out.println(GIVEAWAY_COLUMN.format(bill.getPurchase().getProductName(),bill.getGiveAwayQuantity()));
            }
        }
    }

    private void printTotalPurchasedAmount(TotalBillDto dto) {
        System.out.println(TOTAL_PRICE.format(dto.getTotalQuantity(),Integer.toString(dto.getTotalPrice())));
    }

    private void printTotalOnSaleAmount(TotalBillDto dto) {
        System.out.println(DISCOUNT_WITH_PROMOTION.format(-dto.getTotalPromotionDiscount()));
    }

    private void printMembershipSaleAmount(TotalBillDto dto){
        System.out.println(DISCOUNT_WITH_MEMBERSHIP.format(-dto.getMemberShipDiscount()));
    }

    private void printTheAmountCharged(TotalBillDto dto){
        System.out.println(RESULT_PRICE.format(dto.getFinalPrice()));
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



}
