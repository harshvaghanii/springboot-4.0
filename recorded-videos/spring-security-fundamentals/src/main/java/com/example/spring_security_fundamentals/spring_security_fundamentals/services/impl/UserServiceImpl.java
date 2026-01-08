package com.example.spring_security_fundamentals.spring_security_fundamentals.services.impl;

import com.example.spring_security_fundamentals.spring_security_fundamentals.dto.UserDTO;
import com.example.spring_security_fundamentals.spring_security_fundamentals.dto.auth.SignUpDTO;
import com.example.spring_security_fundamentals.spring_security_fundamentals.entities.User;
import com.example.spring_security_fundamentals.spring_security_fundamentals.exceptions.InvalidCredentialException;
import com.example.spring_security_fundamentals.spring_security_fundamentals.exceptions.ResourceNotFoundException;
import com.example.spring_security_fundamentals.spring_security_fundamentals.repositories.UserRepository;
import com.example.spring_security_fundamentals.spring_security_fundamentals.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User with username: " + username + " not found!"));
    }

    @Override
    public UserDTO signUp(SignUpDTO signUpDTO) {
        log.info("I'm in the signUp service method: {}", signUpDTO);
        if (userExists(signUpDTO.getEmail())) {
            throw new InvalidCredentialException("User with email " + signUpDTO.getEmail() + " already exists!");
        }

        User toSave = modelMapper.map(signUpDTO, User.class);
        toSave.setPassword(passwordEncoder.encode(toSave.getPassword()));
        User savedUser = userRepository.save(toSave);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    private boolean userExists(String email) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        return existingUser.isPresent();
    }
}
