package com.tapash.global_exceptions;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
public class ApiResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String suggestion;
    private Map<String, String> details;

    public static ApiResponse error(String message,int status,LocalDateTime localDateTime){
        return ApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .message("ALREADY EXSISTS")
                .build();
    }


    public static ApiResponse error(String message) {
        return ApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Error")
                .message(message)
                .build();
    }

    public static ApiResponse error(String message, String suggestion) {
        return ApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("something wrong with api")
                .message(message)
                .suggestion(suggestion)
                .build();
    }

    public static ApiResponse error(String message, Map<String, String> details) {
        return ApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Validation failed")
                .message(message)
                .details(details)
                .suggestion(details.toString())
                .build();
    }

}