package store.domain;

import store.constant.PurchaseErrorMessage;
import store.exception.PurchaseException;

public class Purchase{
    private String productName;
    private int quantity;

    public Purchase(String productName, int quantity){
        this.productName = productName;
        this.quantity = quantity;
        validate(productName, quantity);
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

    public String getProductName(){
        return this.productName;
    }
}