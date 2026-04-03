package com.tapash.global_exceptions.category_ex;

public class CategoryAlreadyExsists extends RuntimeException{
    public CategoryAlreadyExsists() {
    }

    public CategoryAlreadyExsists(String message) {
        super(message);
    }

    public CategoryAlreadyExsists(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryAlreadyExsists(Throwable cause) {
        super(cause);
    }

    public CategoryAlreadyExsists(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
