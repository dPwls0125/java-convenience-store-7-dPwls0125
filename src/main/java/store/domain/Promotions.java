package store.domain;

import store.constant.PromotionErrorMessage;
import store.exception.PromotionException;

import java.util.HashMap;
import java.util.Map;

public class Promotions {
    private Map<String,Promotion> promotions = new HashMap<>();

    public void addPromotion(String PromotionName, Promotion promotion){
        promotions.put(PromotionName, promotion);
    }

    public void deletePromotion(String PromotionName){
        promotions.remove(PromotionName);
    }

    public void cointains(String PromotionName){
        if(!promotions.containsKey(PromotionName)){
            throw PromotionException.from(PromotionErrorMessage.INVALID_PROMOTION_NAME);
        }
    }

    public Promotion getValidPromotion(String PromotionName){
        cointains(PromotionName);
        Promotion promotion = promotions.get(PromotionName);
        promotion.validateDate();
        return promotion;
    }
}
