package com.example.project_management_system.service;

import com.example.project_management_system.dto.TenantSignupDTO;
import com.example.project_management_system.entity.Role;
import com.example.project_management_system.entity.Tenant;
import com.example.project_management_system.entity.Users;
import com.example.project_management_system.repository.RoleRepository;
import com.example.project_management_system.repository.TenantRepository;
import com.example.project_management_system.repository.UserRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class SignupService
{
    private final TenantRepository tenantRepo;
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;

    private final PasswordEncoder passwordEncoder;

    public SignupService(TenantRepository tenantRepo, UserRepository userRepo, RoleRepository roleRepo, PasswordEncoder passwordEncoder)
    {
        this.tenantRepo = tenantRepo;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }


    public Tenant tenantSignUp(TenantSignupDTO dto)
    {
        System.out.println("DTO ARGUMENT");
        System.out.println(dto.getName() + " " + dto.getEmail() + " " + dto.getPassword());
        // create a new tenant
        // with name
        Tenant tenant = new Tenant(dto.getName());
        Tenant createdTenant = tenantRepo.saveAndFlush(tenant);

        // create an admin role
        Role role = new Role(createdTenant, "admin");
        Role createdRole = roleRepo.saveAndFlush(role);

        // create new user
        Users user = new Users(dto.getEmail(), passwordEncoder.encode(dto.getPassword()), createdTenant, createdRole);
        userRepo.saveAndFlush(user);
        return createdTenant;
    }


}