package com.example.spring_security_fundamentals.spring_security_fundamentals.services.impl;

import com.example.spring_security_fundamentals.spring_security_fundamentals.exceptions.ResourceNotFoundException;
import com.example.spring_security_fundamentals.spring_security_fundamentals.repositories.UserRepository;
import com.example.spring_security_fundamentals.spring_security_fundamentals.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User with username: " + username + " not found!"));
    }
}
