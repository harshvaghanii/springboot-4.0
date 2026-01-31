package com.vaghani.linkedin.user_service.services.impl;

import com.vaghani.linkedin.user_service.dto.SignupRequestDTO;
import com.vaghani.linkedin.user_service.dto.UserDTO;
import com.vaghani.linkedin.user_service.entities.User;
import com.vaghani.linkedin.user_service.enums.Role;
import com.vaghani.linkedin.user_service.repositories.UserRepository;
import com.vaghani.linkedin.user_service.services.AuthService;
import com.vaghani.linkedin.user_service.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDTO signUp(SignupRequestDTO signupRequestDTO) {
        User user = modelMapper.map(signupRequestDTO, User.class);
        user.setPassword(PasswordUtil.hashPassword(signupRequestDTO.getPassword()));
        user.setRoles(Set.of(Role.USER));
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }
}
