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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    @Value("${deploy.env}")
    String deployEnv;

    @PostMapping(path = "/signup")
    public UserDTO signUp(@RequestBody SignUpDTO signUpDTO) {
        return userService.signUp(signUpDTO);
    }


    @PostMapping(path = "/login")
    public OAuthTokenDTO login(@RequestBody LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response) {
        OAuthTokenDTO oAuthTokenDTO = authService.login(loginDTO);
        Cookie cookie = new Cookie("refreshToken", oAuthTokenDTO.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setSecure(deployEnv.equals("prod"));
        cookie.setPath("/auth/refresh");
        response.addCookie(cookie);
        return oAuthTokenDTO;
    }

    @PostMapping(path = "/refresh")
    public OAuthTokenDTO refresh(HttpServletRequest request) {
        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("refreshToken"))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new AuthenticationServiceException("Refresh Token not found inside the cookie! Please login again!"));
        OAuthTokenDTO oAuthTokenDTO = authService.refreshToken(refreshToken);
        return oAuthTokenDTO;
    }

}
