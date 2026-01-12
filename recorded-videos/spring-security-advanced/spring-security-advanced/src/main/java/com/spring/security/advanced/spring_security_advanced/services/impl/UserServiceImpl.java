package com.spring.security.advanced.spring_security_advanced.services.impl;

import com.spring.security.advanced.spring_security_advanced.entities.User;
import com.spring.security.advanced.spring_security_advanced.exceptions.ResourceNotFoundException;
import com.spring.security.advanced.spring_security_advanced.repositories.UserRepository;
import com.spring.security.advanced.spring_security_advanced.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User with username: " + email + " not found!"));

    }

    @Override
    public User getById(Long userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with User ID: " + userId + " not found!"));
    }
}
