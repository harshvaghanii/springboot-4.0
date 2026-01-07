package com.example.spring_security_fundamentals.spring_security_fundamentals;

import com.example.spring_security_fundamentals.spring_security_fundamentals.entities.User;
import com.example.spring_security_fundamentals.spring_security_fundamentals.services.auth.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SpringSecurityFundamentalsApplicationTests {

    @Autowired
    JwtService jwtService;

    @Test
    void testJwtTokenGeneration() {
        User user = new User();
        user.setId(10014L);
        user.setEmail("harsh@gmail.com");
        user.setPassword("password");
        String jwtToken = jwtService.generateToken(user);
        System.out.println("This is the token that is generated: " + jwtToken);

        Long id = jwtService.extractUserName(jwtToken);
        System.out.println("Actual ID: " + user.getId());
        System.out.println("JWT Returned ID: " + id);
        assertTrue(Objects.equals(id, user.getId()));
    }

    @Test
    void contextLoads() {
    }

}
