package com.example.homework_week_05.homework_week05.dto.auth;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OAuthTokenDto {

    private LocalDateTime creationTime;

    private String oAuthToken;

}
