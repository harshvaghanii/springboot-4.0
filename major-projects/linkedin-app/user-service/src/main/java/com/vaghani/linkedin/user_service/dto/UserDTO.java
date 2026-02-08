package com.vaghani.linkedin.user_service.dto;

import com.vaghani.linkedin.user_service.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {

    private Long id;

    private String name;

    private String email;

    private Set<Role> roles;

}
