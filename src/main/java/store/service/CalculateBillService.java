package store.service;

import store.domain.BillPerProduct;
import store.domain.Customer;
import store.domain.Purchase;
import store.dto.AvailableStockDto;

import java.util.List;

public class CalculateBillService {
    private static final int MAX_MEMBERSHIP_PRICE = 8000;
    private double calculateAppliedMemberShipPrice(int remainPrice){
        if(remainPrice * 0.15 > MAX_MEMBERSHIP_PRICE) return 8000;
        return remainPrice * 0.15;
    }


    public void updateBillPerProduct(AvailableStockDto dto, Customer customer, Purchase purchase){
        BillPerProduct bill = new BillPerProduct(dto,purchase);
        customer.addBillForProducts(bill);
    }

    public void getTotalBill(Customer customer){
        List<BillPerProduct> bills = customer.getBillPerProducts();
        int totalQuantity = 0;
        int totalPrice = 0;
        int totalPromotionDiscount = 0;
        boolean memberShipDiscount;
        int finalPrice;

        for(BillPerProduct b : bills){
            totalQuantity += b.getPurchase().getQuantity();
            totalPrice += b.getTotalPrice();
            totalPromotionDiscount += b.getPriceInPromotion();
//            if()  {
//
//            }
        }
    }


}
