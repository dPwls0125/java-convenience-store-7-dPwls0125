package store.exception;

import store.constant.PromotionErrorMessage;

public class PromotionException extends IllegalArgumentException {

    public PromotionException(final PromotionErrorMessage message) {
        super(message.getMessage());
    }

    public static PromotionException from(final PromotionErrorMessage message) {
        return new PromotionException(message);
    }
}
