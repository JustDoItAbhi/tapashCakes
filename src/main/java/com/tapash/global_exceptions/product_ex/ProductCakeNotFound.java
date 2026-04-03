package com.tapash.global_exceptions.product_ex;

public class ProductCakeNotFound extends RuntimeException{
    public ProductCakeNotFound() {
    }

    public ProductCakeNotFound(String message) {
        super(message);
    }

    public ProductCakeNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductCakeNotFound(Throwable cause) {
        super(cause);
    }

    public ProductCakeNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
