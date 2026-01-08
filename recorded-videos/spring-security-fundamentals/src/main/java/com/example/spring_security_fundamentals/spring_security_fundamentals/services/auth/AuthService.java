package com.example.spring_security_fundamentals.spring_security_fundamentals.services.auth;

import com.example.spring_security_fundamentals.spring_security_fundamentals.dto.auth.LoginDTO;
import com.example.spring_security_fundamentals.spring_security_fundamentals.dto.auth.OAuthTokenDto;
import com.example.spring_security_fundamentals.spring_security_fundamentals.entities.User;
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

    public OAuthTokenDto login(LoginDTO loginDTO) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

        User user = (User) authentication.getPrincipal();
        String token = jwtService.generateToken(user);

        OAuthTokenDto oAuthTokenDto = OAuthTokenDto
                .builder()
                .creationTime(LocalDateTime.now())
                .oAuthToken(token)
                .build();
        return oAuthTokenDto;
    }


}
