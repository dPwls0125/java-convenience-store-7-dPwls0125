package store.domain;

import store.constant.PurchaseErrorMessage;
import store.exception.PurchaseException;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private List<Purchase> purchases = new ArrayList<Purchase>();

    public void addPurchase(Purchase purchase) {
        for(Purchase p : purchases){
            if(p.getProductName().equals(purchase.getProductName())){
                throw PurchaseException.from(PurchaseErrorMessage.INVALID_QUANTITY);
            }
        }
        purchases.add(purchase);
    }
}
