package store.domain;

import camp.nextstep.edu.missionutils.DateTimes;
import net.bytebuddy.asm.Advice;
import store.constant.PromotionErrorMessage;
import store.exception.PromotionException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Promotion {
    private final String promotionName;
    private int purchaseAmount;
    private int extraAmount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Promotion(String promotionName, int purchaseAmount, int extraAmount, LocalDateTime startDate, LocalDateTime endDate) {
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
        if(this.endDate.isBefore(DateTimes.now())){
            throw PromotionException.from(PromotionErrorMessage.ALREADY_TERMINATED_PROMOTION);
        }
    }

    public int getExtraAmount(){
        return this.extraAmount;
    }

    public int getPurchaseAmount(){
        return this.purchaseAmount;
    }

    public String getPromotionName() {
        return promotionName;
    }
}
