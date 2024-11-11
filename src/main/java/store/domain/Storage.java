package store.domain;

import store.constant.ProductErrorMessage;
import store.exception.ProductException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Storage {
    Map<String, ArrayList<Product>> products = new HashMap<>();

    public void addProduct(String productName, Product product){
        if (products.containsKey(productName)) {
            products.get(productName).add(product);
            return;
        }
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        products.put(productName, productList);
    }

    public void deleteProduct(String productName){
        products.remove(productName);
    }

    public void contatins(String productName){
        if(!products.containsKey(productName)){
            throw ProductException.from(ProductErrorMessage.DUBLICATION_PRODUCT_NAME);
        }
    }
}
