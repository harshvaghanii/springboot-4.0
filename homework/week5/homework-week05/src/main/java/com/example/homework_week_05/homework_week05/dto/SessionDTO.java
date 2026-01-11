package com.example.homework_week_05.homework_week05.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionDTO {

    private Long userId;

    private String accessToken;

}
