package com.vaghani.linkedin.user_service.controllers.auth;

import com.vaghani.linkedin.user_service.dto.SignupRequestDTO;
import com.vaghani.linkedin.user_service.dto.UserDTO;
import com.vaghani.linkedin.user_service.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping(path = "/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignupRequestDTO signupRequestDTO) {

    }

}
