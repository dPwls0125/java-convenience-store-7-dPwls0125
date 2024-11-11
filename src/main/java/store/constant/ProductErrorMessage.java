package store.constant;

public enum ProductErrorMessage {

    DUBLICATION_PRODUCT_NAME("[Error] 동일한 상품명이 기존에 등록되어 있습니다.");

    private String message;

    ProductErrorMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
