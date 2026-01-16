package com.spring.security.advanced.spring_security_advanced.services.auth;

import com.spring.security.advanced.spring_security_advanced.dto.auth.LoginDTO;
import com.spring.security.advanced.spring_security_advanced.dto.auth.OAuthTokenDTO;
import com.spring.security.advanced.spring_security_advanced.entities.User;
import com.spring.security.advanced.spring_security_advanced.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public OAuthTokenDTO login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

        User user = (User) authentication.getPrincipal();
        String jwtToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        OAuthTokenDTO oAuthTokenDTO = OAuthTokenDTO
                .builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .createdAt(LocalDateTime.now())
                .build();
        return oAuthTokenDTO;

    }


    public OAuthTokenDTO refreshToken(String refreshToken) {

        Long userId = jwtService.extractUserId(refreshToken);
        User user = userService.getById(userId);

        String accessToken = jwtService.generateAccessToken(user);
        OAuthTokenDTO oAuthTokenDTO = OAuthTokenDTO
                .builder()
                .refreshToken(refreshToken)
                .accessToken(accessToken)
                .createdAt(LocalDateTime.now())
                .build();
        return oAuthTokenDTO;
    }
}
