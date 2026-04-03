package com.tapash.global_exceptions.category_ex;

public class TitledCakeNotFound extends RuntimeException{
    public TitledCakeNotFound() {
    }

    public TitledCakeNotFound(String message) {
        super(message);
    }

    public TitledCakeNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public TitledCakeNotFound(Throwable cause) {
        super(cause);
    }

    public TitledCakeNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
