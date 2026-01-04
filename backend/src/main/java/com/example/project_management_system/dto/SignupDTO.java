package com.example.project_management_system.dto;

import com.example.project_management_system.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupDTO {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role; // should default to admin and i think i will handle this in the signupgit s service
}