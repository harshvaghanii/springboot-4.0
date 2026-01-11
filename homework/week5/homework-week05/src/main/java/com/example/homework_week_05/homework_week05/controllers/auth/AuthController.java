package com.example.homework_week_05.homework_week05.controllers.auth;

import com.example.homework_week_05.homework_week05.dto.UserDTO;
import com.example.homework_week_05.homework_week05.dto.auth.LoginDTO;
import com.example.homework_week_05.homework_week05.dto.auth.OAuthTokenDto;
import com.example.homework_week_05.homework_week05.dto.auth.SignUpDTO;
import com.example.homework_week_05.homework_week05.services.UserService;
import com.example.homework_week_05.homework_week05.services.auth.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping(path = "/signup")
    UserDTO signUp(@RequestBody SignUpDTO signUpDTO) {
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
