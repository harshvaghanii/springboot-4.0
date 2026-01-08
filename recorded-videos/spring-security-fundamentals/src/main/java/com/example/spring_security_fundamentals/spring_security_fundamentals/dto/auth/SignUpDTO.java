package com.example.spring_security_fundamentals.spring_security_fundamentals.dto.auth;

import lombok.Data;

@Data
public class SignUpDTO {

    private String email;

    private String password;

    private String name;

}
