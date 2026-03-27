package com.spring.security.advanced.spring_security_advanced.repositories;

import com.spring.security.advanced.spring_security_advanced.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
