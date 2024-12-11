package store.service;

import store.domain.Customer;
import store.domain.Product;
import store.domain.Promotion;
import store.domain.Storage;
import store.dto.AvailableStockDto;
import store.dto.BonusQuantityAndPriceDto;
import store.dto.PurchaseStockDto;

import java.util.Map;

public class PromotionService {
    Storage storage;
    public PromotionService(Storage storage){
        this.storage = storage;
    }
    // 구매를 원하는 상품에 대한 프로모션 존재 여부 확인
    public boolean IsPromotionExist(Product product){
        Promotion promotion = product.getPromotion();
        if(promotion != null)
            return true;
        return false;
    }

    /**
     * 프로모션이 있는 상품의 경우, 현재 구매 수량 외에 추가 지급 가능한 갯수 반환
     * <p>
     * ex) 2+1 promotion에서 5개만 가져온 경우
     */
    public int getCalculatedAvailableBonusQuantity(Product product, int purchaseWantQuantity) {
        Promotion promotion = product.getPromotion();
        // 프로모션 적용 가능한 수량
        int purchaseForExtra = promotion.getPurchaseAmount();
        // 프로모션 적용시 추가 증정량
        int promotionExtra = promotion.getExtraAmount();
        // 프로모션 적용시 총 개수
        int quantityInOneSet = promotion.getQuantityInOneSet();

        // 프로모션을 적용해서 추가 지급이 가능한지 확인
        int promotionStock = product.getPromotionQuantity();
        if (purchaseWantQuantity >= promotionStock) {
            return 0;
        }

        // 프로모션이 적용된 상품의 총 개수
        int promotionQualifiedQuantity = (purchaseWantQuantity / quantityInOneSet) * quantityInOneSet;
        // 현재 구매하려는 상품 개수가 추가로 프로모션 적용이 가능한지 확인
        if (!isAvailableExtra(purchaseWantQuantity, purchaseForExtra, quantityInOneSet)) {
            return 0;
        }

        // 추가 지급 시 총 상품 개수
        int totalQuantity = promotionQualifiedQuantity + quantityInOneSet;

        // 만약 총 지급 개수가 프로모션 총 재고 수량보다 적은 경우
        if (promotionStock < totalQuantity) {
            // 가능한 만큼만 추가 지급
            return promotionStock - purchaseWantQuantity;
        }
        return totalQuantity - purchaseWantQuantity;
    }

    private boolean isAvailableExtra(int purchaseWantQuantity, int purchaseForExtra, int quantityInOneSet) {
        return purchaseWantQuantity % quantityInOneSet >= purchaseForExtra;
    }

    public AvailableStockDto getAvailableStorage(String productName,int purchaseWantQuantity){

        Product product = storage.getProduct(productName);
        int promotionStock = product.getPromotionQuantity();
        int nonPromotionStock =  product.getNonPromotionQuantity();

        int availablePromotionStock = Math.min(promotionStock,purchaseWantQuantity);
        int remainingQuantity = purchaseWantQuantity - availablePromotionStock;
        int availableNonPromotionStock = Math.min(nonPromotionStock, Math.max(remainingQuantity,0));

        return new AvailableStockDto(availablePromotionStock,
                availableNonPromotionStock,
                availableNonPromotionStock + availablePromotionStock);
    }

    public int calculateAppliedPromotionQuantity(Product product, int availablePromotionQuantity){
        Promotion promotion = product.getPromotion();
        int promotionQuantityPerSet = promotion.getQuantityInOneSet();
        return (availablePromotionQuantity / promotionQuantityPerSet)  * promotionQuantityPerSet;
    }

    public int calculateGiveAwayQuantity(Product product ,int availablePromotionQuantity ){
        Promotion promotion = product.getPromotion();
        int promotionQuantityPerSet = promotion.getQuantityInOneSet();
        int numberOfSet = availablePromotionQuantity / promotionQuantityPerSet;
        return numberOfSet * promotion.getExtraAmount();
    }


    public void updateStorage(PurchaseStockDto dto, Product product){
        product.setNonPromotionQuantity(product.getNonPromotionQuantity() - dto.getPurchasedQuantityInRegular());
        product.setPromotionQuantity(product.getPromotionQuantity()-dto.getPurchasedQuantityInPromotion());
    }

    public BonusQuantityAndPriceDto getAppliedBonusQuanityAndPrice(int quantityWhichPurchasedInPromotionStock, Product product ){
        Promotion promotion = product.getPromotion();
        int quantityInOneSet = promotion.getQuantityInOneSet();
        int bonusQuantity = (quantityWhichPurchasedInPromotionStock / quantityInOneSet) * promotion.getExtraAmount();
        return new BonusQuantityAndPriceDto(bonusQuantity, bonusQuantity * product.getPrice());
    }

}