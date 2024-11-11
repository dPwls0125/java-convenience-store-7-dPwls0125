package store.constant;

public enum ProductErrorMessage {

    DUBLICATION_PRODUCT_NAME("[Error] 유효하지 않은 상품 이름 입니다.");

    private String message;

    ProductErrorMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
