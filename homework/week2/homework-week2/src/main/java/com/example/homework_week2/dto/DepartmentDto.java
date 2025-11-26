package com.example.homework_week2.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DepartmentDto {

    private Long id;

    private String title;

    private Boolean isActive;

    private LocalDateTime createdAt;

}
