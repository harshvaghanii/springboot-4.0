package com.vaghani.linkedin.user_service.services;

import com.vaghani.linkedin.user_service.dto.LoginRequestDTO;
import com.vaghani.linkedin.user_service.dto.LoginResponseDTO;
import com.vaghani.linkedin.user_service.dto.SignupRequestDTO;
import com.vaghani.linkedin.user_service.dto.UserDTO;

public interface AuthService {

    UserDTO signUp(SignupRequestDTO signupRequestDTO);

    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}
