package com.tapash.global_exceptions;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CategoryMessageException {
    private LocalDateTime exceptionTime;
    private String message;
    int code;

    public CategoryMessageException(LocalDateTime exceptionTime, String message, int code) {
        this.exceptionTime = exceptionTime;
        this.message = message;
        this.code = code;
    }
}
