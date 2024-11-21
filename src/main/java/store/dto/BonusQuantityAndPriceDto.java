package store.dto;

public class BonusQuantityAndPriceDto {

    int bonusAppliedQuantity;
    int bonusAppliedPrice;

    public BonusQuantityAndPriceDto(int bonusQuantity, int bonusAppliedPrice) {
        this.bonusAppliedQuantity = bonusQuantity;
        this.bonusAppliedPrice = bonusAppliedPrice;
    }
}
