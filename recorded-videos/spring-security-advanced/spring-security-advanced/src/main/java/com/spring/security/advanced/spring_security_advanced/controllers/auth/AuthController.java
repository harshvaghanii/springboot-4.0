package com.spring.security.advanced.spring_security_advanced.controllers.auth;

import com.spring.security.advanced.spring_security_advanced.dto.SignUpDTO;
import com.spring.security.advanced.spring_security_advanced.dto.UserDTO;
import com.spring.security.advanced.spring_security_advanced.dto.auth.LoginDTO;
import com.spring.security.advanced.spring_security_advanced.dto.auth.OAuthTokenDTO;
import com.spring.security.advanced.spring_security_advanced.services.UserService;
import com.spring.security.advanced.spring_security_advanced.services.auth.AuthService;
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
    public UserDTO signUp(@RequestBody SignUpDTO signUpDTO) {
        return userService.signUp(signUpDTO);
    }


    @PostMapping(path = "/login")
    public OAuthTokenDTO login(@RequestBody LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response) {
        OAuthTokenDTO oAuthTokenDTO = authService.login(loginDTO);
        String token = oAuthTokenDTO.getOAuthToken();
        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return oAuthTokenDTO;
    }

}
