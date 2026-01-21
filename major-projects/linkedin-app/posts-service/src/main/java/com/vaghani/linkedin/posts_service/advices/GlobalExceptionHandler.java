package com.vaghani.linkedin.posts_service.advices;

import com.vaghani.linkedin.posts_service.exceptions.BadRequestException;
import com.vaghani.linkedin.posts_service.exceptions.ResourceNotFoundException;
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
                .message(exception.getMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<?>> handleBadRequestException(BadRequestException exception) {
        ApiError apiError = ApiError
                .builder()
                .message(exception.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();
        return buildErrorResponseEntity(apiError);
    }


    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(new ApiResponse<>(apiError), apiError.getHttpStatus());
    }


}
