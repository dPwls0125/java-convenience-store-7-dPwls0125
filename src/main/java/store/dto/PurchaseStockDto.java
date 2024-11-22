package store.dto;

public class PurchaseStockDto {
    int purchasedQuantityInRegular;
    int purchasedQuantityInPromotion;

    private PurchaseStockDto(int purchasedQuantityInRegular, int purchasedQuantityInPromotion){
        this.purchasedQuantityInRegular = purchasedQuantityInRegular;
        this.purchasedQuantityInPromotion = purchasedQuantityInPromotion;
    }

    public static PurchaseStockDto of(int purchasedQuantityInRegular, int purchasedQuantityInPromotion){
        return new PurchaseStockDto(purchasedQuantityInRegular,purchasedQuantityInPromotion);
    }

    public int getPurchasedQuantityInRegular() {
        return purchasedQuantityInRegular;
    }

    public int getPurchasedQuantityInPromotion() {
        return purchasedQuantityInPromotion;
    }
}
