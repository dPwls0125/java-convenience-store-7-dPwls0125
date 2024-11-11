package store.constant;

public enum PromotionErrorMessage {
    ALREADY_TERMINATED_PROMOTION("[Error] 이미 종료된 프로모션 입니다."),
    INVALID_DATE_SET("[Error] 유효하지 않은 프로모션 입니다."),
    INVALID_PROMOTION_NAME("[Error] 유효하지 않은 프로모션 이름 입니다.");

    private String message;

    PromotionErrorMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
