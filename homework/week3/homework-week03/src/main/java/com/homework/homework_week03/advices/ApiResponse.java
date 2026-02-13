package com.homework.homework_week03.advices;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {

    private final LocalDateTime timeStamp;

    private T data;

    private ApiError apiError;


    public ApiResponse() {
        this.timeStamp = LocalDateTime.now();
    }

    public ApiResponse(ApiError apiError) {
        this();
        this.apiError = apiError;
    }

    public ApiResponse(T data) {
        this();
        this.data = data;
    }


}
