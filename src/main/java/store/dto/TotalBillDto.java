package store.dto;

// Todo: 빌더 구현~
public class TotalBillDto {
    private int totalQuantity;
    private int totalPrice;
    private int totalPromotionDiscount;
    private int memberShipDiscount;
    private int finalPrice;
    private TotalBillDto(int totalQuantity,int totalPrice, int totalPromotionDiscount, int memberShipDiscount, int finalPrice) {
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
        this.totalPromotionDiscount = totalPromotionDiscount;
        this.memberShipDiscount = memberShipDiscount;
        this.finalPrice = finalPrice;
    }
    public static TotalBillDto of(int totalQuantity,int totalPrice, int totalPromotionDiscount, int memberShipDiscount, int finalPrice){
        return new TotalBillDto(totalQuantity,totalPrice, totalPromotionDiscount, memberShipDiscount, finalPrice);
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalPromotionDiscount() {
        return totalPromotionDiscount;
    }

    public void setTotalPromotionDiscount(int totalPromotionDiscount) {
        this.totalPromotionDiscount = totalPromotionDiscount;
    }

    public int getMemberShipDiscount() {
        return memberShipDiscount;
    }

    public void setMemberShipDiscount(int memberShipDiscount) {
        this.memberShipDiscount = memberShipDiscount;
    }

    public int getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(int finalPrice) {
        this.finalPrice = finalPrice;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
