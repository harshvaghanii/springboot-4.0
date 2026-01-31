package com.vaghani.linkedin.user_service.services.impl;

import com.vaghani.linkedin.user_service.dto.LoginRequestDTO;
import com.vaghani.linkedin.user_service.dto.LoginResponseDTO;
import com.vaghani.linkedin.user_service.dto.SignupRequestDTO;
import com.vaghani.linkedin.user_service.dto.UserDTO;
import com.vaghani.linkedin.user_service.entities.User;
import com.vaghani.linkedin.user_service.enums.Role;
import com.vaghani.linkedin.user_service.exceptions.BadRequestException;
import com.vaghani.linkedin.user_service.exceptions.ResourceExistsException;
import com.vaghani.linkedin.user_service.repositories.UserRepository;
import com.vaghani.linkedin.user_service.services.AuthService;
import com.vaghani.linkedin.user_service.services.auth.JwtService;
import com.vaghani.linkedin.user_service.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    @Override
    public UserDTO signUp(SignupRequestDTO signupRequestDTO) {
        if (findByEmail(signupRequestDTO.getEmail()).isPresent()) {
            log.error("User with email: {} already exists! Please login!", signupRequestDTO.getEmail());
            throw new ResourceExistsException("User already exists with the given email! Please login");
        }
        User user = modelMapper.map(signupRequestDTO, User.class);
        user.setPassword(PasswordUtil.hashPassword(signupRequestDTO.getPassword()));
        user.setRoles(Set.of(Role.USER));
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        Optional<User> existingUser = findByEmail(loginRequestDTO.getEmail());

        if (existingUser.isEmpty()
                || !PasswordUtil.checkPassword(loginRequestDTO.getPassword(), existingUser.get().getPassword())) {
            throw new BadRequestException("Invalid Credentials! Please try again!");
        }

        String accessToken = jwtService.generateAccessToken(existingUser.get());
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(accessToken);
        return loginResponseDTO;
    }


    private Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
