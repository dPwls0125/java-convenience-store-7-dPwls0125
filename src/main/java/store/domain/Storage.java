package store.domain;

import store.constant.ProductErrorMessage;
import store.exception.ProductException;

import java.util.HashMap;
import java.util.Map;

public class Storage {
    private Map<String, Product> products = new HashMap<>();
    public void addProduct(String productName, Product product){
        checkProductNameDuplicated(productName);
        products.put(productName, product);
    }
    public void deleteProduct(String productName){
        products.remove(productName);
    }

    public void getProduct(){
        for(String key : products.keySet()){
            System.out.println(products.get(key).getProductName());
        }
    }

    private void isProductExist(String productName){
        if(!products.containsKey(productName))
            throw ProductException.from(ProductErrorMessage.NOT_EXIST_PRODUCT);
    }

    public void checkProductNameDuplicated(String productName){
        if(products.containsKey(productName)){
            throw ProductException.from(ProductErrorMessage.DUBLICATION_PRODUCT_NAME);
        }
    }

    public Map<String, Product> getProducts() {
        return products;
    }
    public boolean productHasPromotion(String productName){
        return products.get(productName).hasPromotion();
    }
}
