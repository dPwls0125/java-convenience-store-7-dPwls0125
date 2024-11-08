package store.exception;

import store.constant.ErrorMessage;

public class PurchaseException extends IllegalArgumentException{
    public PurchaseException(final ErrorMessage errorMessage){
        super(errorMessage.getMessage());
    }

    public static PurchaseException from(final ErrorMessage errorMessage){
        return new PurchaseException(errorMessage);
    }
}
