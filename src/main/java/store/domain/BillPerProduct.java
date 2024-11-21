package store.domain;

import store.dto.AvailableStockDto;
import store.dto.BillPerProductDto;

public class BillPerProduct {
    Purchase purchase;
    private int giveAwayQuantity;
    private int totalQuantity;
    private int priceInPromotion;
    private int totalPrice;

    public BillPerProduct(Purchase purchase, int giveAwayQuantity,
                          int priceInPromotion, int totalPrice) {
        this.purchase = purchase;
        this.giveAwayQuantity = giveAwayQuantity;
        this.priceInPromotion = priceInPromotion;
        this.totalPrice = totalPrice;
    }

    public BillPerProduct(AvailableStockDto dto, Purchase purchase) {
        this.giveAwayQuantity = dto.getAvailableStockForPromotion(); // todo: 다시 계산
        this.totalQuantity = dto.getTotalAvailableStock();
        this.priceInPromotion = giveAwayQuantity * purchase.getProduct().getPrice();
        this.totalPrice = (totalQuantity - giveAwayQuantity) * purchase.getProduct().getPrice();
    }

    public int getGiveAwayQuantity() {
        return giveAwayQuantity;
    }

    public int getPriceInPromotion() {
        return priceInPromotion;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public static BillPerProduct of(Purchase purchase, BillPerProductDto dto){
        return new BillPerProduct(purchase, dto.getTotalGiveAwayQuantity(), dto.getPriceInPromotion(), dto.getTotalPrice());
    }
}
