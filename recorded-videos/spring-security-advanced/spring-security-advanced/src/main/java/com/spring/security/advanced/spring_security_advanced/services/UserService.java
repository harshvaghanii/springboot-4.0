package com.spring.security.advanced.spring_security_advanced.services;

import com.spring.security.advanced.spring_security_advanced.entities.User;

public interface UserService {

    User getById(Long userId);

}
