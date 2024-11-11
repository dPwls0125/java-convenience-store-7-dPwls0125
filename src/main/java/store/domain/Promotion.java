package store.domain;

import net.bytebuddy.asm.Advice;
import store.constant.PromotionErrorMessage;
import store.exception.PromotionException;

import java.time.LocalDate;

public class Promotion {
    private final String promotionName;
    private int purchaseAmount;
    private int extraAmount;
    private LocalDate startDate;
    private LocalDate endDate;

    public Promotion(String promotionName, int purchaseAmount, int extraAmount, LocalDate startDate, LocalDate endDate) {
        this.promotionName = promotionName;
        this.purchaseAmount = purchaseAmount;
        this.extraAmount = extraAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        validate();
    }

    private void validate(){
        validateDate();
        validateStartDate();
    }

    private void validateStartDate(){
        if(this.startDate.isAfter(this.endDate)){
            throw PromotionException.from(PromotionErrorMessage.INVALID_DATE_SET);
        }
    }

    public void validateDate(){
        if(this.endDate.isBefore(LocalDate.now())){
            throw PromotionException.from(PromotionErrorMessage.ALREADY_TERMINATED_PROMOTION);
        }
    }

    public int getExtraAmount(){
        return this.extraAmount;
    }

    public int getPurchaseAmount(){
        return this.purchaseAmount;
    }

}
