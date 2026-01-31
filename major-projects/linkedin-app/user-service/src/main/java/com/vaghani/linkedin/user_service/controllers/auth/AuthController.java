package com.vaghani.linkedin.user_service.controllers.auth;

import com.vaghani.linkedin.user_service.dto.LoginRequestDTO;
import com.vaghani.linkedin.user_service.dto.LoginResponseDTO;
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

    private final AuthService authService;

    @PostMapping(path = "/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignupRequestDTO signupRequestDTO) {
        UserDTO userDTO = authService.signUp(signupRequestDTO);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO loginResponseDTO = authService.login(loginRequestDTO);
        return ResponseEntity.ok(loginResponseDTO);
    }

}
