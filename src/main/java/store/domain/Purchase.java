package store.domain;

import store.constant.PurchaseErrorMessage;
import store.exception.PurchaseException;

public class Purchase{
    private Product product;
    private int quantity;
    private int price;

    public Purchase(Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
        validate(product.getProductName(), quantity);
        this.price = product.getPrice() * quantity;
    }

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
}