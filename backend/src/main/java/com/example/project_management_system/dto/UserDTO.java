package com.example.project_management_system.dto;

import com.example.project_management_system.entity.Role;
import com.example.project_management_system.entity.Tenant;

public class UserDTO {
    private String firstname;
    private String lastname;
    private String email;
    private Tenant tenant;
    private Role role;
}