package store.constant;

public enum PurchaseErrorMessage {
    INVALID_QUANTITY("[Error] 구매 수량은 1이상이어야 합니다."),
    DUPLICATE_PRODUCT_NAME("[Error] 중복된 상품명이 존재합니다."),
    OUT_OF_STOCK("Error : 재고가 부족합니다.");
    private String message;

    PurchaseErrorMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
