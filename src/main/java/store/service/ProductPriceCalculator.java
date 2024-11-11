package store.service;

import store.domain.Product;
import store.domain.Promotion;
import store.dto.BillPerProductDto;

public class ProductPriceCalculator {

    public BillPerProductDto getBillsPerProduct(int quantity, Promotion promotion, Product product){

        int quantityInPromotion = calculatePurchaseQuantityInPromotion(promotion,product);
        int quantityInOriginal = quantity - quantityInPromotion;
        int discount = calculateDiscount(promotion,product);
        int totalGiveAwayQuantity = calculateExtraQuantity(promotion.getExtraAmount(), promotion.getPurchaseAmount(), promotion.getExtraAmount());
        int totalPrice = calculateOriginalPrice(product.getPrice(),quantity) - discount;

        return BillPerProductDto.of(totalGiveAwayQuantity, quantityInPromotion, quantityInOriginal, discount, totalPrice);
    }

    public int calculatePurchaseQuantityInPromotion(Promotion promotion, Product product) {
        int promotionPurchaseAmount = promotion.getPurchaseAmount();
        int promotionExtraAmount = promotion.getExtraAmount();
        int setForPromotion = availableSetInPromotionStock(product.getPromotionQuantity(), promotionPurchaseAmount, promotionExtraAmount);
        return setForPromotion * (promotionExtraAmount + promotionPurchaseAmount);
    }

    private int calculateExtraQuantity(int promotionStock, int purchaseAmount, int extraAmount){
        int setForPromotion = availableSetInPromotionStock(promotionStock, purchaseAmount, extraAmount);
        return setForPromotion * extraAmount;
    }

    private int availableSetInPromotionStock(int promotionStock, int promotionPurchaseAmount, int promotionExtraAmount){
        int calculateQuantityInSetOfPromotion = calculateQuantityInSetOfPromotion(promotionPurchaseAmount, promotionExtraAmount);
        return promotionStock / calculateQuantityInSetOfPromotion;
    }

    private int calculateOriginalPrice(int price, int quantity){
        return price * quantity;
    }

    private int calculateDiscount(Promotion promotion, Product product){
        int extraQuantity = calculateExtraQuantity(promotion.getExtraAmount(), promotion.getPurchaseAmount(), promotion.getExtraAmount());
        return extraQuantity * product.getPrice();
    }

    private int calculateQuantityInSetOfPromotion(int purchaseAmount, int extraAmount){
        return extraAmount + purchaseAmount;
    }
}
