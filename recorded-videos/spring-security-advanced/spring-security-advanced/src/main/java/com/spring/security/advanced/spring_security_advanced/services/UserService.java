package com.spring.security.advanced.spring_security_advanced.services;

import com.spring.security.advanced.spring_security_advanced.dto.SignUpDTO;
import com.spring.security.advanced.spring_security_advanced.dto.UserDTO;
import com.spring.security.advanced.spring_security_advanced.entities.User;

public interface UserService {

    UserDTO signUp(SignUpDTO signUpDTO);

    User getById(Long userId);

}
