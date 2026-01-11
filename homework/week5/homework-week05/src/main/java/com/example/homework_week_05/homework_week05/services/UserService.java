package com.example.homework_week_05.homework_week05.services;

import com.example.homework_week_05.homework_week05.dto.UserDTO;
import com.example.homework_week_05.homework_week05.dto.auth.SignUpDTO;
import com.example.homework_week_05.homework_week05.entities.User;

public interface UserService {

    UserDTO signUp(SignUpDTO signUpDTO);

    User getUserById(Long userId);

}
