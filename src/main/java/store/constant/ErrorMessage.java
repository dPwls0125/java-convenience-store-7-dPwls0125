package store.constant;

public enum ErrorMessage {
    INVALID_QUANTITY("[Error] 구매 수량은 1이상이어야 합니다.");

    private String message;

    ErrorMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
