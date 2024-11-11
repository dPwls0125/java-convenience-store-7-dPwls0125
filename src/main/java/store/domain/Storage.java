package store.domain;

import java.util.HashMap;
import java.util.Map;

public class Storage {
    Map<String,Product> products = new HashMap<>();

    public void addProduct(String productName, Product product){
        products.put(productName, product);
    }

    public void deleteProduct(String productName){
        products.remove(productName);
    }



}
