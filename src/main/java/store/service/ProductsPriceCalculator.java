package store.service;

import store.domain.BillPerProduct;
import store.dto.TotalBillDto;

import java.util.List;

public class ProductsPriceCalculator {

    public TotalBillDto getTotalBillDTo(List<BillPerProduct>bills, boolean membership){
        int totalPrice = calculateTotalPrice(bills);
        int totalPromotionDiscount = calculateTotalPromotionDiscount(bills);
        int memberShipDiscount = membership ? memershipDiscount(totalPrice,totalPromotionDiscount) : 0;
        int finalPrice = totalPrice - totalPromotionDiscount - memberShipDiscount;
        return TotalBillDto.of(totalPrice, totalPromotionDiscount, memberShipDiscount, finalPrice);
    }

    private int calculateTotalPromotionDiscount(List<BillPerProduct> bills){
        return bills.stream()
                .mapToInt(BillPerProduct::getPriceInPromotion)
                .sum();
    }
    private int calculateTotalPrice(List<BillPerProduct> bills){
        return bills.stream()
                .mapToInt(bill -> bill.getPurchase().getPrice())
                .sum();
    }
    private int finalPrice(List<BillPerProduct> bills){
        return calculateTotalPrice(bills) - calculateTotalPromotionDiscount(bills);
    }
    private int memershipDiscount(int totalPrice, int totalPromotionDiscount){
        int discount = (int) (totalPrice * 0.3);
        if(discount > 8000){
            return 8000;
        }
        return discount;
    }
}
