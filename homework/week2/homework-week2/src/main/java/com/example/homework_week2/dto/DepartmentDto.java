package com.example.homework_week2.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DepartmentDto {

    private Long id;

    @NotEmpty(message = "Required Field title is Empty!")
    private String title;

    @NotNull(message = "Required Field isActive is Null!")
    private Boolean isActive;

    private LocalDateTime createdAt;

}
