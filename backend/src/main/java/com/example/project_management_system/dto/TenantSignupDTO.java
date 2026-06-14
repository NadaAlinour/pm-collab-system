package com.example.project_management_system.dto;


import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TenantSignupDTO {
    private String name; // like a company name
    private String firstname;
    private String lastname;
    private String email;
    private String password;
}
