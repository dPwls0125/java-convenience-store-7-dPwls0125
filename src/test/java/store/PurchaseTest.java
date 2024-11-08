package store;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Purchase{
    private String productName;
    private int quantity;

    public Purchase(String productName, int quantity){
        this.productName = productName;
        this.quantity = quantity;
        validate(productName, quantity);
    }


    private void validate(String productName, int quantity){
        validateQuantity(quantity);
    }

    private void validateQuantity(int quantity){
        if(quantity <= 0){
            throw new IllegalArgumentException("[Error] 구매 수량은 1이상이어야 합니다.");
        }
    }

    public int getQuantity(){
        return quantity;
    }

    public String getPrice(){
        return this.productName;
    }
}

public class PurchaseTest {

    @DisplayName("구매 수량은 1이어야 한다.")
    @Test
    void 구매_수량은_1이상(){
        //given
        Purchase purchase = new Purchase("콩나물",1);
        // when
        int quantity = purchase.getQuantity();
        // then
        assertThat(quantity).isGreaterThan(0);
    }


    @Test
    @DisplayName("구매 수량이 1이하이면 안된다.")
    void 구매_수량은_1이하이면_안된다(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Purchase("콩나물", 0);
        });
    }

}
