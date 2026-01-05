package com.example.project_management_system.controller;

import com.example.project_management_system.dto.TenantSignupDTO;
import com.example.project_management_system.entity.Tenant;
import com.example.project_management_system.service.SignupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/signup")

public class SignupController
{
    private final SignupService signupService;

    public SignupController(SignupService signupService)
    {
        this.signupService = signupService;
    }

    @PostMapping
    public ResponseEntity<Tenant> signupTenant(@RequestBody TenantSignupDTO dto)
    {
        return ResponseEntity.ok(signupService.tenantSignUp(dto));
    }

}