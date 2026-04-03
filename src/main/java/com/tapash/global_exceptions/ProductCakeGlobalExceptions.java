package com.tapash.global_exceptions;

import com.tapash.global_exceptions.product_ex.ProductCakeAlreadyExists;
import com.tapash.global_exceptions.product_ex.ProductCakeNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ProductCakeGlobalExceptions {
    @ExceptionHandler(ProductCakeNotFound.class)
    public ResponseEntity<CategoryMessageException> notExsits(ProductCakeNotFound ex) {
        CategoryMessageException exception = new CategoryMessageException(
                LocalDateTime.now(),
                ex.getMessage(),
                404

        );
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ProductCakeAlreadyExists.class)
    public ResponseEntity<CategoryMessageException> alreadyExsits(ProductCakeAlreadyExists ex) {
        CategoryMessageException exception = new CategoryMessageException(
                LocalDateTime.now(),
                ex.getMessage(),
                409

        );
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }
}
