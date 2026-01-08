package com.example.spring_security_fundamentals.spring_security_fundamentals.repositories;

import com.example.spring_security_fundamentals.spring_security_fundamentals.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
