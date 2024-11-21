package store.constant;

public enum PurchaseErrorMessage {
    INVALID_QUANTITY("[Error] 구매 수량은 1이상이어야 합니다."),
    DUPLICATE_PRODUCT_NAME("[Error] 이미 구매한 상품입니다."),
    INVALID_PRODUCT_NAME("[Error] 유효하지 않은 상품명입니다."),
    PLEASE_ANSWER_YN("[Error] Y 또는 N을 입력해주세요."),
    PLEASE_ANSWER_RIGHT_WAY("[Error] 옳은 방식으로 입력해주세요."),
    OUT_OF_STOCK("Error : 재고가 부족합니다.");
    private String message;

    PurchaseErrorMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
