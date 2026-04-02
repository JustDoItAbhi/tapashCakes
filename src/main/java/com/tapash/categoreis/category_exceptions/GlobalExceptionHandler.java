package com.tapash.categoreis.category_exceptions;


import com.tapash.categoreis.category_exceptions.excpetions.CakeCategoryNotFound;
import com.tapash.categoreis.category_exceptions.excpetions.CategoryAlreadyExsists;
import com.tapash.entity.CakeCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final Pattern COLUMN_NAME_PATTERN = Pattern.compile("for column '([^']+)'");
    private static final Pattern DUPLICATE_PATTERN = Pattern.compile("Duplicate entry '([^']+)'");

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.debug("Validation error: {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ApiResponse.error("Validation failed", errors);
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        log.error("Data integrity violation: {}", ex.getMessage(), ex);

        Throwable rootCause = ex.getRootCause();
        String message = rootCause != null ? rootCause.getMessage() : ex.getMessage();

        if (message.contains("Data too long for column")) {
            Matcher matcher = COLUMN_NAME_PATTERN.matcher(message);
            String columnName = matcher.find() ? matcher.group(1) : "field";
            return ApiResponse.error(
                    String.format("The value for '%s' is too long", columnName),
                    "Maximum length exceeded. Please shorten the text."
            );
        }

        if (message.contains("duplicate entry")) {
            Matcher matcher = DUPLICATE_PATTERN.matcher(message);
            String value = matcher.find() ? matcher.group(1) : "value";
            return ApiResponse.error(
                    String.format("Duplicate entry: '%s' already exists", value),
                    "Please use a unique value"
            );
        }

        return ApiResponse.error("Database constraint violation", message);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse handleGenericException(Exception ex) {
        log.error("Unexpected error occurred", ex);
        return ApiResponse.error("Internal server error", "Please contact support");
    }

    @ExceptionHandler(CategoryAlreadyExsists.class)
    public ResponseEntity<CategoryMessageException> alreadyExsits(CategoryAlreadyExsists ex) {
    CategoryMessageException exception=new CategoryMessageException(
            LocalDateTime.now(),
            ex.getMessage(),
            409

    );
    return new ResponseEntity<>(exception,HttpStatus.CONFLICT);
    }
    @ExceptionHandler(CakeCategoryNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CategoryMessageException> notfound(CakeCategoryNotFound ex) {
        CategoryMessageException exception=new CategoryMessageException(
                LocalDateTime.now(),
                ex.getMessage(),
                404

        );
        return new ResponseEntity<>(exception,HttpStatus.NOT_FOUND);
    }
}