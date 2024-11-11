package store;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.domain.Product;
import store.domain.Storage;
import store.exception.ProductException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductTest {
    private Storage storage = new Storage();
    @Test
    @DisplayName("같은 상품명을 가진 상품을 재고에 추가 할 수 없다.")
    void 같은_상품명을_가진_상품을_재고에_추가_할_수_없다() {
        // given
        Product product1 = new Product("콜라", 1000, 1, 1, null);
        Product product2 = new Product("콜라", 1000, 1, 1, null);
        // when
        storage.addProduct(product1.getProductName(), product1);
        // then
        assertThrows(ProductException.class, () -> {
            storage.addProduct(product2.getProductName(), product2);
        });
    }

}
