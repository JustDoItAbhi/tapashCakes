package com.tapash.global_exceptions.product_ex;

public class ProductCakeAlreadyExists extends RuntimeException{
    public ProductCakeAlreadyExists() {
    }

    public ProductCakeAlreadyExists(String message) {
        super(message);
    }

    public ProductCakeAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductCakeAlreadyExists(Throwable cause) {
        super(cause);
    }

    public ProductCakeAlreadyExists(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
