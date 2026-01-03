package com.example.prod_ready_features.demo.advices;

import com.example.prod_ready_features.demo.exceptions.ResourceNotFoundException;
import com.example.prod_ready_features.demo.exceptions.RestClientException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFoundException(ResourceNotFoundException exception) {
        ApiError apiError = ApiError
                .builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<ApiResponse<?>> handleRestClientException(RestClientException exception) {
        ApiError apiError = ApiError
                .builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }

    public ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(new ApiResponse<>(apiError), apiError.getHttpStatus());
    }

}
