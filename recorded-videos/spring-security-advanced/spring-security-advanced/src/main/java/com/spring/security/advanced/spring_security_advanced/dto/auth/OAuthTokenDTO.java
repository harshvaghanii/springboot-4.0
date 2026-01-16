package com.spring.security.advanced.spring_security_advanced.dto.auth;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OAuthTokenDTO {

    private String accessToken;

    private String refreshToken;

    private LocalDateTime createdAt;

}
