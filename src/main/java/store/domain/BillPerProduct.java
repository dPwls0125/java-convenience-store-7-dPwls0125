package store.domain;

import store.dto.AvailableStockDto;
import store.dto.BillPerProductDto;

public class BillPerProduct {
    Purchase purchase;
    private int totalQuantity;
    private int giveAwayQuantity;

    public BillPerProduct(Purchase purchase, int totalPrice, int giveAwayQuantity) {
        this.purchase = purchase;
        this.totalQuantity = totalPrice;
        this.giveAwayQuantity = giveAwayQuantity;
    }

    public BillPerProduct(AvailableStockDto dto, Purchase purchase) {
        this.giveAwayQuantity = dto.getAvailableStockForPromotion(); // todo: 다시 계산
        this.totalQuantity = dto.getTotalAvailableStock();
    }

    public int getGiveAwayQuantity() {
        return giveAwayQuantity;
    }
    public Purchase getPurchase() {
        return purchase;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }
}
