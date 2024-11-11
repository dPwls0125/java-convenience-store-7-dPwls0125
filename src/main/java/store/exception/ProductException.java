package store.exception;

import store.constant.ProductErrorMessage;

public class ProductException extends IllegalArgumentException {

    public ProductException(final ProductErrorMessage message) {
        super(message.getMessage());
    }

    public static ProductException from(final ProductErrorMessage message) {
        return new ProductException(message);
    }

}
