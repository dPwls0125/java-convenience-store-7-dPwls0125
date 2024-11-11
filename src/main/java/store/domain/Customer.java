package store.domain;

import store.constant.PurchaseErrorMessage;
import store.dto.BillPerProductDto;
import store.exception.PurchaseException;
import store.service.PriceCalculator;

import java.util.ArrayList;
import java.util.List;

import static store.constant.PurchaseErrorMessage.OUT_OF_STOCK;

public class Customer {
    private List<Purchase> purchases = new ArrayList<Purchase>();
    private List<BillPerProduct> billPerProducts = new ArrayList<BillPerProduct>();
    private final PriceCalculator priceCalculator = new PriceCalculator();

    public void addPurchase(Purchase purchase) {
        checkDuplicatePurchase(purchase);
        purchases.add(purchase);
        addBillPerProducts(purchase);
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    private void addBillPerProducts(Purchase purchase) {
        Product product = purchase.getProduct();
        Promotion promotion = product.getPromotion();
        BillPerProductDto dto = priceCalculator.getBillsPerProduct(purchase.getQuantity(), promotion, product);
        BillPerProduct billPerProduct = BillPerProduct.of(product.getProductName(),dto);
        billPerProducts.add(billPerProduct);
    }

    private void checkDuplicatePurchase(Purchase purchase) {
        for (Purchase p : purchases) {
            if (p.getProductName().equals(purchase.getProductName())) {
                throw PurchaseException.from(OUT_OF_STOCK);
            }
        }
    }

}
