package com.spring.security.advanced.spring_security_advanced.services.impl;

import com.spring.security.advanced.spring_security_advanced.dto.SignUpDTO;
import com.spring.security.advanced.spring_security_advanced.dto.UserDTO;
import com.spring.security.advanced.spring_security_advanced.entities.User;
import com.spring.security.advanced.spring_security_advanced.exceptions.ResourceNotFoundException;
import com.spring.security.advanced.spring_security_advanced.repositories.UserRepository;
import com.spring.security.advanced.spring_security_advanced.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User with username: " + email + " not found!"));

    }

    @Override
    public UserDTO signUp(SignUpDTO signUpDTO) {
        if (userExists(signUpDTO.getEmail())) {
            throw new BadCredentialsException("User with email: " + signUpDTO.getEmail() + " already exists! Please login!");
        }

        User toSave = modelMapper.map(signUpDTO, User.class);
        toSave.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
        User savedUser = userRepository.save(toSave);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public User getById(Long userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with User ID: " + userId + " not found!"));
    }

    private boolean userExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

}
