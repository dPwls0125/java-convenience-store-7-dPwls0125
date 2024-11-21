package store.service;

import store.domain.Customer;
import store.domain.Product;
import store.domain.Purchase;
import store.domain.Storage;
import store.exception.PurchaseException;

import java.util.List;

public class PurchaseService {
    private Storage storage;
    public void addPurchase(Customer customer, String productName, int quantity) throws PurchaseException {
        Product product = storage.getProduct(productName);
        Purchase purchase = Purchase.of(product,quantity);
        customer.addPurchase(purchase);
    }

    public PurchaseService(Storage storage){
        this.storage = storage;
    }





}
