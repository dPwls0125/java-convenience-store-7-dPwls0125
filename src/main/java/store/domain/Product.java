package store.domain;

public class Product {
    private String name;
    private int price;
    private Integer nonPromotionQuantity;
    private Integer promotionQuantity;
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

    public Integer getPrice(){
        return this.price;
    }

    public void setNonPromotionQuantity(int quantity){
        this.nonPromotionQuantity = quantity;
    }

    public void setPromotionQuantity(int quantity){
        this.promotionQuantity = quantity;
    }

    public boolean hasPromotion(){
        return promotion != null;
    }

    public String getName() {
        return name;
    }

    public Integer getNonPromotionQuantity() {
        return nonPromotionQuantity;
    }

    public int getPromotionQuantity(){
        if(promotionQuantity == null) return 0;
        return this.promotionQuantity;
    }

    public int getTotalQuantity(){return getNonPromotionQuantity() + getPromotionQuantity();}

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("productName: " + this.name + "\n");
        sb.append("productPrice: " + this.price + "\n");
        sb.append("nonPromotion quantity: " + this.nonPromotionQuantity+ "\n");
        sb.append("promotion quantity: " + this.promotionQuantity + "\n");
        sb.append("promotion: " + this.promotion.getPromotionName() + "\n");

        return sb.toString();
    }
}
