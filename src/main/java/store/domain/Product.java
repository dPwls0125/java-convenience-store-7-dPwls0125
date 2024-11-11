package store.domain;

public class Product {
    private String name;
    private int price;
    private int nonPromotionQuantity;
    private int promotionQuantity;
    private Promotion promotion;

    public Product(String name, int price, int nonPromotionQuantity, int promotionQuantity, Promotion promotion) {
        this.name = name;
        this.price = price;
        this.nonPromotionQuantity = nonPromotionQuantity;
        this.promotionQuantity = promotionQuantity;
        this.promotion = promotion;
    }

    public String getProductName(){
        return this.name;
    }

    public boolean hasPromotion(){
        return promotion != null;
    }
}
