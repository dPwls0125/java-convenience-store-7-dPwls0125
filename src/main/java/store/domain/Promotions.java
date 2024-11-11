package store.domain;

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

    public void cointainsPromotion(String PromotionName){
        promotions.containsKey(PromotionName);
    }

}
