package store;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.domain.Promotion;
import store.exception.PromotionException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PromotionAndMembershipTest {
    @Test
    @DisplayName("프로모션 기간이 지난 프로모션을 추가 할 수 없다.")
    void 프로모션_기간이_지난_프로모션을_추가_할_수_없다() {
        assertThrows(PromotionException.class, () -> {
            new Promotion("반짝할인",1,1, LocalDate.parse("2024-10-10"),LocalDate.parse("2024-10-20"));
        });
    }

    @Test
    @DisplayName("프로모션 시작일이 종료일보다 늦을 수 없다.")
    void 프로모션_시작일이_종료일보다_늦을_수_없다() {
        assertThrows(PromotionException.class, () -> {
            new Promotion("반짝할인",1,1, LocalDate.parse("2024-11-20"),LocalDate.parse("2024-11-11"));
        });
    }
}