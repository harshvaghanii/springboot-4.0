package com.example.spring_security_fundamentals.spring_security_fundamentals.dto.auth;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OAuthTokenDto {

    private LocalDateTime creationTime;

    private String oAuthToken;

}
