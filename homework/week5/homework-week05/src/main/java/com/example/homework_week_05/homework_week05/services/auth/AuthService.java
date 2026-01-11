package com.example.homework_week_05.homework_week05.services.auth;

import com.example.homework_week_05.homework_week05.dto.auth.LoginDTO;
import com.example.homework_week_05.homework_week05.dto.auth.OAuthTokenDto;
import com.example.homework_week_05.homework_week05.entities.Session;
import com.example.homework_week_05.homework_week05.entities.User;
import com.example.homework_week_05.homework_week05.services.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final SessionService sessionService;

    public OAuthTokenDto login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

        User user = (User) authentication.getPrincipal();
        String token = jwtService.generateToken(user);

        sessionService.deleteByUserId(user.getId());
        Session session = sessionService.createSession(user.getId(), token);

        OAuthTokenDto oAuthTokenDto = OAuthTokenDto
                .builder()
                .creationTime(session.getCreatedAt())
                .oAuthToken(session.getAccessToken())
                .build();
        System.out.println("Printing the oAuthTokenDTO: " + oAuthTokenDto);
        return oAuthTokenDto;
    }

}
