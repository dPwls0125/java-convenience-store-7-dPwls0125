package store.constant;

public enum PurchaseErrorMessage {
    INVALID_QUANTITY("[Error] 구매 수량은 1이상이어야 합니다."),
    DUPLICATE_PRODUCT_NAME("[Error] 중복된 상품명이 존재합니다."),
    INVALID_PRODUCT_NAME("[Error] 유효하지 않은 상품명입니다."),
    PELASE_ANSWER_YN("[Error] Y 또는 N을 입력해주세요."),
    OUT_OF_STOCK("Error : 재고가 부족합니다.");
    private String message;

    PurchaseErrorMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
