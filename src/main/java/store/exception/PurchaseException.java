package store.exception;

import store.constant.PurchaseErrorMessage;

public class PurchaseException extends IllegalArgumentException {

    public PurchaseException(final PurchaseErrorMessage purchaseErrorMessage) {
        super(purchaseErrorMessage.getMessage());
    }

    public static PurchaseException from(final PurchaseErrorMessage purchaseErrorMessage) {
        return new PurchaseException(purchaseErrorMessage);
    }

}
