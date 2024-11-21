package store.dto;

public class AvailableStockDto {
    int availableStockForPromotion;
    int availableStockForNonPromotion;
    int totalAvailableStock;


    public AvailableStockDto(int promotion, int nonPromotion, int total){
        this.availableStockForPromotion = promotion;
        this.availableStockForNonPromotion = nonPromotion;
        this.totalAvailableStock = total;
    }

    public int getAvailableStockForPromotion() {
        return availableStockForPromotion;
    }

    public int getAvailableStockForNonPromotion() {
        return availableStockForNonPromotion;
    }

    public int getTotalAvailableStock() {
        return totalAvailableStock;
    }

    public void setAvailableStockForPromotion(int availableStockForPromotion) {
        this.availableStockForPromotion = availableStockForPromotion;
    }

    public void setAvailableStockForNonPromotion(int availableStockForNonPromotion) {
        this.availableStockForNonPromotion = availableStockForNonPromotion;
    }

    public void setTotalAvailableStock(int totalAvailableStock) {
        this.totalAvailableStock = totalAvailableStock;
    }
}
