package store.domain;

import store.dto.BillPerProductDto;

public class BillPerProduct {
    Purchase purchase;
    private int giveAwayQuantity;
    private int quantityInPromotion;
    private int quantityInOriginal;
    private int priceInPromotion;
    private int totalPrice;

    public BillPerProduct(Purchase purchase, int giveAwayQuantity,
                          int quantityInPromotion, int quantityInOriginal,
                          int priceInPromotion, int totalPrice) {

        this.purchase = purchase;
        this.giveAwayQuantity = giveAwayQuantity;
        this.quantityInPromotion = quantityInPromotion;
        this.quantityInOriginal = quantityInOriginal;
        this.priceInPromotion = priceInPromotion;
        this.totalPrice = totalPrice;
    }

    public int getGiveAwayQuantity() {
        return giveAwayQuantity;
    }

    public int getQuantityInPromotion() {
        return quantityInPromotion;
    }

    public int getQuantityInOriginal() {
        return quantityInOriginal;
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
        return new BillPerProduct(purchase, dto.getTotalGiveAwayQuantity(), dto.getQuantityInPromotion(), dto.getQuantityInOriginal(), dto.getPriceInPromotion(), dto.getTotalPrice());
    }
}
