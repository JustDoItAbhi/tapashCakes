package com.tapash.global_exceptions.category_ex;

public class CakeCategoryNotFound extends RuntimeException{
    public CakeCategoryNotFound() {
    }

    public CakeCategoryNotFound(String message) {
        super(message);
    }

    public CakeCategoryNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public CakeCategoryNotFound(Throwable cause) {
        super(cause);
    }

    public CakeCategoryNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
