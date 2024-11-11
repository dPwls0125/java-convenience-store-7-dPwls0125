package store.domain;

import store.constant.ProductErrorMessage;
import store.exception.ProductException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Storage {
    Map<String, Product> products = new HashMap<>();

    public void addProduct(String productName, Product product){
        contatins(productName);
        products.put(productName, product);
    }

    public void deleteProduct(String productName){
        products.remove(productName);
    }

    public void productsClear(){
        products.clear();
    }

    public void contatins(String productName){
        if(products.containsKey(productName)){
            throw ProductException.from(ProductErrorMessage.DUBLICATION_PRODUCT_NAME);
        }
    }

    public boolean productHasPromotion(String productName){
        return products.get(productName).hasPromotion();
    }
}
