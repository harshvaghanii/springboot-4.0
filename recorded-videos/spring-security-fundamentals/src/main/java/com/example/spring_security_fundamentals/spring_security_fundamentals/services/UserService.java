package com.example.spring_security_fundamentals.spring_security_fundamentals.services;

import com.example.spring_security_fundamentals.spring_security_fundamentals.dto.UserDTO;
import com.example.spring_security_fundamentals.spring_security_fundamentals.dto.auth.SignUpDTO;
import com.example.spring_security_fundamentals.spring_security_fundamentals.entities.User;

public interface UserService {

    UserDTO signUp(SignUpDTO signUpDTO);

    User getUserById(Long userId);
}
