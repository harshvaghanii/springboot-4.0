package com.example.homework_week2.advices;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {

    private T data;

    private ApiError apiError;

    private final LocalDateTime timeStamp;

    public ApiResponse() {
        this.timeStamp = LocalDateTime.now();
    }

    public ApiResponse(T data) {
        this();
        this.data = data;
        this.apiError = null;
    }

    public ApiResponse(ApiError apiError) {
        this();
        this.data = null;
        this.apiError = apiError;
    }

}
