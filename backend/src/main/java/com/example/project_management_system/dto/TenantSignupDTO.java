package com.example.project_management_system.dto;


import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TenantSignupDTO {
    private String name;
    private String email;
    private String password;
}
