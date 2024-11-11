package store;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.config.PromotionConfig;
import store.domain.Product;
import store.domain.Promotion;
import store.domain.Promotions;
import store.domain.Purchase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class PurchaseTest {

    Promotions promotions = PromotionConfig.loadPromotions();
    @DisplayName("구매 수량은 1이어야 한다.")
    @Test
    void 구매_수량은_1이상() {
        //given
        Promotion promotion = promotions.getValidPromotion("반짝할인");
        Purchase purchase = Purchase.of(new Product("콩나물",1000,1,1, promotion), 1);
        // when
        int quantity = purchase.getQuantity();
        // then
        assertThat(quantity).isGreaterThan(0);
    }

    @Test
    @DisplayName("구매 수량이 1이하이면 안된다.")
    void 구매_수량은_1이하이면_안된다() {
        assertThrows(IllegalArgumentException.class, () -> {
            Promotion promotion = promotions.getValidPromotion("반짝할인");
            Purchase purchase = Purchase.of(new Product("콩나물",1000,1,1, promotion), 0);
        });
    }

}
