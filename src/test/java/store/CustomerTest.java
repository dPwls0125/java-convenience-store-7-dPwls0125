package store;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.config.PromotionConfig;
import store.constant.PurchaseErrorMessage;
import store.domain.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomerTest {
    Promotions promotions = PromotionConfig.loadPromotions();
    @Test
    @DisplayName("중복 되는 상품명을 제시 할 수 없다.")
    void 중복_되는_상품명을_제시_할_수_없다() {
        // given
        Customer customer = new Customer();
        Promotion promotion = promotions.getValidPromotion("반짝할인");
        Purchase purchase = Purchase.of(new Product("콩나물",1000,1,1, promotion), 1);
        customer.addPurchase(purchase);
        Purchase purchase2 = Purchase.of(new Product("콩나물",1000,1,1, promotion), 1);
        // when // then
        assertThatThrownBy(()->{
            customer.addPurchase(purchase2);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PurchaseErrorMessage.INVALID_QUANTITY.getMessage());
    }

}
