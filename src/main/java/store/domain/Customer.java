package store.domain;

import store.dto.BillPerProductDto;
import store.exception.PurchaseException;
import store.service.ProductPriceCalculator;

import java.util.ArrayList;
import java.util.List;

import static store.constant.PurchaseErrorMessage.OUT_OF_STOCK;

public class Customer {
    private List<Purchase> purchases = new ArrayList<Purchase>();
    private List<BillPerProduct> billPerProducts = new ArrayList<BillPerProduct>();
    private boolean applicateMembership;
    private final ProductPriceCalculator productPriceCalculator = new ProductPriceCalculator();

    public void addPurchase(Purchase purchase) {
        checkDuplicatePurchase(purchase);
        purchases.add(purchase);
        addBillPerProductsForPromotion(purchase);
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void addBillPerProductsForPromotion(Purchase purchase) {
        Product product = purchase.getProduct();
        Promotion promotion = product.getPromotion();
        BillPerProductDto dto = productPriceCalculator.getBillsPerProduct(purchase.getQuantity(), promotion, product);
        BillPerProduct billPerProduct = BillPerProduct.of(purchase,dto);
        billPerProducts.add(billPerProduct);
    }

    public void addBillPerProductsForNonPromotion(Purchase purchase) {
        Product product = purchase.getProduct();
        BillPerProductDto dto = BillPerProductDto.of(0, 0, purchase.getQuantity(), 0, product.getPrice() * purchase.getQuantity());
        BillPerProduct billPerProduct = BillPerProduct.of(purchase,dto);
        billPerProducts.add(billPerProduct);
    }

    private void checkDuplicatePurchase(Purchase purchase) {
        for (Purchase p : purchases) {
            if (p.getProductName().equals(purchase.getProductName())) {
                throw PurchaseException.from(OUT_OF_STOCK);
            }
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
