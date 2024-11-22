package store.domain;

import store.exception.PurchaseException;


import java.util.ArrayList;
import java.util.List;

import static store.constant.PurchaseErrorMessage.DUPLICATE_PRODUCT_NAME;
import static store.constant.PurchaseErrorMessage.OUT_OF_STOCK;

public class Customer {
    private static final List<Purchase> shoppingCart = new ArrayList<Purchase>();
    private static final List<BillPerProduct> billPerProducts = new ArrayList<BillPerProduct>();
    private boolean applicateMembership;

    public void addPurchase(Purchase purchase) {
        checkDuplicatePurchase(purchase);
        validateStock(purchase);
        shoppingCart.add(purchase);
    }

    public List<Purchase> getPurchases() {
        return shoppingCart;
    }

    public void addBillForProducts(BillPerProduct bill) {
        billPerProducts.add(bill);
    }

    public void printPurchaseList() {
        for (Purchase p: shoppingCart) {
            System.out.println(p);
        }
    }


    private void checkDuplicatePurchase(Purchase purchase) {
        for (Purchase p : shoppingCart) {
            if (p.getProductName().equals(purchase.getProductName())) {
                throw PurchaseException.from(DUPLICATE_PRODUCT_NAME);
            }
        }
    }

    private void validateStock(Purchase purchase){
        Product product = purchase.getProduct();
        if(product.getTotalQuantity() < purchase.getQuantity()){
            throw PurchaseException.from(OUT_OF_STOCK);
        }
    }

    public List<BillPerProduct> getBillPerProducts() {
        return billPerProducts;
    }

    public boolean isApplicateMembership() {
        return applicateMembership;
    }

    public void setApplicateMembership(boolean applicateMembership) {
        this.applicateMembership = applicateMembership;
    }
}
