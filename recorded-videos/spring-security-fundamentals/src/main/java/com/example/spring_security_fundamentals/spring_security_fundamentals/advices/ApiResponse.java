package com.example.spring_security_fundamentals.spring_security_fundamentals.advices;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {

    private T data;

    private ApiError apiError;

    private LocalDateTime timeStamp;

    public ApiResponse() {
        this.timeStamp = LocalDateTime.now();
    }

    public ApiResponse(T data) {
        this();
        this.data = data;
    }

    public ApiResponse(ApiError apiError) {
        this();
        this.apiError = apiError;
    }

}
