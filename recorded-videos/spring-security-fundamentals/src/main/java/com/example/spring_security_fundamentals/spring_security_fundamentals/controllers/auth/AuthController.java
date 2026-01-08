package com.example.spring_security_fundamentals.spring_security_fundamentals.controllers.auth;

import com.example.spring_security_fundamentals.spring_security_fundamentals.dto.UserDTO;
import com.example.spring_security_fundamentals.spring_security_fundamentals.dto.auth.LoginDTO;
import com.example.spring_security_fundamentals.spring_security_fundamentals.dto.auth.OAuthTokenDto;
import com.example.spring_security_fundamentals.spring_security_fundamentals.dto.auth.SignUpDTO;
import com.example.spring_security_fundamentals.spring_security_fundamentals.services.UserService;
import com.example.spring_security_fundamentals.spring_security_fundamentals.services.auth.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping(path = "/signup")
    public UserDTO signUp(@RequestBody SignUpDTO signUpDTO) {
        return userService.signUp(signUpDTO);
    }

    @PostMapping(path = "/login")
    public OAuthTokenDto login(@RequestBody LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response) {
        OAuthTokenDto oAuthTokenDto = authService.login(loginDTO);
        String token = oAuthTokenDto.getOAuthToken();
        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return oAuthTokenDto;
    }

}
