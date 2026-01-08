package com.example.homework_week_05.homework_week05.services.impl;

import com.example.homework_week_05.homework_week05.dto.UserDTO;
import com.example.homework_week_05.homework_week05.dto.auth.SignUpDTO;
import com.example.homework_week_05.homework_week05.entities.User;
import com.example.homework_week_05.homework_week05.exceptions.InvalidCredentialException;
import com.example.homework_week_05.homework_week05.repositories.UserRepository;
import com.example.homework_week_05.homework_week05.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO signUp(SignUpDTO signUpDTO) {

        if (userExists(signUpDTO.getEmail())) {
            throw new InvalidCredentialException("User with email " + signUpDTO.getEmail() + " already exists!");
        }

        User toSaveUser = modelMapper.map(signUpDTO, User.class);
        toSaveUser.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
        User savedUser = userRepository.save(toSaveUser);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public User getUserById(Long userId) {
        return null;
    }

    private boolean userExists(String email) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        return existingUser.isPresent();
    }
}
