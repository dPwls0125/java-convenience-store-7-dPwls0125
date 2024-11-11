package store;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.constant.PurchaseErrorMessage;
import store.domain.Customer;
import store.domain.Purchase;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomerTest {
    @Test
    @DisplayName("중복 되는 상품명을 제시 할 수 없다.")
    void 중복_되는_상품명을_제시_할_수_없다() {
        // given
        Customer customer = new Customer();
        customer.addPurchase(new Purchase("콩나물", 1));
        // when // then
        assertThatThrownBy(()->{
            customer.addPurchase(new Purchase("콩나물",3));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PurchaseErrorMessage.INVALID_QUANTITY.getMessage());
    }

}
