package store.dto;

public class BillPerProductDto {
    private int quantityInPromotion;
    private int quantityInOriginal;
    private int priceInPromotion;
    private int totalGiveAwayQuantity;
    private int totalPrice;

    public BillPerProductDto(int totalGiveAwayQuantity, int quantityInPromotion,int quantityInOriginal, int priceInPromotion, int totalPrice) {
        this.totalGiveAwayQuantity = totalGiveAwayQuantity;
        this.quantityInPromotion = quantityInPromotion;
        this.quantityInOriginal = quantityInOriginal;
        this.priceInPromotion = priceInPromotion;
        this.totalPrice = totalPrice;
    }

    public static  BillPerProductDto of(int totalGiveAwayQuantity,int quantityInPromotion, int quantityInOriginal, int priceInPromotion, int totalPrice){
        return new BillPerProductDto(totalGiveAwayQuantity, quantityInPromotion, quantityInOriginal, priceInPromotion, totalPrice);
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

    public int getTotalGiveAwayQuantity() {
        return totalGiveAwayQuantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
