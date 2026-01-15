package com.spring.security.advanced.spring_security_advanced.services.auth;

import com.spring.security.advanced.spring_security_advanced.dto.auth.LoginDTO;
import com.spring.security.advanced.spring_security_advanced.dto.auth.OAuthTokenDTO;
import com.spring.security.advanced.spring_security_advanced.entities.User;
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

    public OAuthTokenDTO login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

        User user = (User) authentication.getPrincipal();
        String jwtToken = jwtService.generateToken(user);

        OAuthTokenDTO oAuthTokenDTO = OAuthTokenDTO
                .builder()
                .oAuthToken(jwtToken)
                .createdAt(LocalDateTime.now())
                .build();
        return oAuthTokenDTO;

    }


}
