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
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
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
                .orElseThrow(() -> new BadCredentialsException("Invalid Credentials! Please try again!"));
    }

    @Override
    public UserDTO signUp(SignUpDTO signUpDTO) {
        if (userExists(signUpDTO.getEmail())) {
            throw new InvalidCredentialException("User with email " + signUpDTO.getEmail() + " already exists!");
        }

        User toSave = modelMapper.map(signUpDTO, User.class);
        toSave.setPassword(passwordEncoder.encode(toSave.getPassword()));
        User savedUser = userRepository.save(toSave);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID: " + userId + " not found!"));
    }

    private boolean userExists(String email) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        return existingUser.isPresent();
    }

    public User getCurrentUser() {

        if (SecurityContextHolder.getContext() == null || SecurityContextHolder.getContext().getAuthentication() == null) {
            log.info("No user is signed in!");
            return null;
        }

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return currentUser;
    }
}
