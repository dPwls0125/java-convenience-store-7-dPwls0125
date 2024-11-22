package store.service;

import store.domain.BillPerProduct;
import store.domain.Customer;
import store.dto.TotalBillDto;
import java.util.List;

public class CalculateBillService {
    private static final int MAX_MEMBERSHIP_PRICE = 8000;
    private int calculateAppliedMemberShipPrice(int remainPrice){
        if(remainPrice * 0.15 > MAX_MEMBERSHIP_PRICE) return 8000;
        return (int) (remainPrice * 0.15);
    }
    public TotalBillDto getTotalBill(Customer customer, boolean isMembershipApply){

        List<BillPerProduct> bills = customer.getBillPerProducts();
        int totalQuantity = 0;
        int totalPrice = 0;
        int totalPromotionDiscount = 0;
        int membershipdiscount = 0;
        int chargedPrice;

        for(BillPerProduct b : bills){
            int productPrice = b.getPurchase().getProduct().getPrice();
            totalQuantity += b.getPurchase().getQuantity();
            totalPrice += productPrice * b.getTotalQuantity() ;
            totalPromotionDiscount += productPrice * b.getGiveAwayQuantity();
        }

        chargedPrice = totalPrice - totalPromotionDiscount - membershipdiscount;
        if(isMembershipApply){
            membershipdiscount = calculateAppliedMemberShipPrice(totalPrice - totalPromotionDiscount);
            TotalBillDto totalBillDto = TotalBillDto.of(totalQuantity,totalPrice,totalPromotionDiscount,membershipdiscount,chargedPrice);
            return totalBillDto ;
        }
        return TotalBillDto.of(totalQuantity,totalPrice,totalPromotionDiscount,0,chargedPrice);
    }
}
