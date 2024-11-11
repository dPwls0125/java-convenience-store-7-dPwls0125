package store.domain;

import store.dto.BillPerProductDto;

public class BillPerProduct {
    private String productName;

    private int totalQuantity;
    private int giveAwayQuantity;
    private int quantityInPromotion;
    private int quantityInOriginal;
    private int priceInPromotion;
    private int totalPrice;

    public BillPerProduct(String productName, int giveAwayQuantity,
                          int quantityInPromotion, int quantityInOriginal,
                          int priceInPromotion, int totalPrice) {
        this.productName = productName;
        this.giveAwayQuantity = giveAwayQuantity;
        this.quantityInPromotion = quantityInPromotion;
        this.quantityInOriginal = quantityInOriginal;
        this.priceInPromotion = priceInPromotion;
        this.totalPrice = totalPrice;
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

    public static BillPerProduct of(String productName, BillPerProductDto dto){
        return new BillPerProduct(productName, dto.getTotalGiveAwayQuantity(), dto.getQuantityInPromotion(), dto.getQuantityInOriginal(), dto.getPriceInPromotion(), dto.getTotalPrice());
    }
}
