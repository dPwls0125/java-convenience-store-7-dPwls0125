package store.domain;

import store.constant.PurchaseErrorMessage;
import store.exception.PurchaseException;

public class Purchase{
    private Product product;
    private int quantity;

    public static Purchase of(Product product, int quantity){
        return new Purchase(product, quantity);
    }

    private Purchase(Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
        validate(product.getProductName(), quantity);
    }

    // 재고 확인 하고 OUT_OF_STOCK 예외 던짐
    private void validateQuantityStock(int quantity){
        if(quantity > product.getNonPromotionQuantity() + product.getPromotionQuantity()){
            throw PurchaseException.from(PurchaseErrorMessage.OUT_OF_STOCK);
        }
    }

    private void validate(String productName, int quantity){
        validateQuantity(quantity);
    }

    private void validateQuantity(int quantity){
        if(quantity <= 0){
            throw PurchaseException.from(PurchaseErrorMessage.INVALID_QUANTITY);
        }
    }

    public int getQuantity(){
        return quantity;
    }
    public Product getProduct(){
        return this.product;
    }
    public String getProductName(){
        return this.product.getProductName();
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("product=" + product.toString() + "\n");
        sb.append("quantity=" + quantity + "\n");
        return sb.toString();
    }
}