package com.example.project_management_system.service;

import com.example.project_management_system.dto.TenantSignupDTO;
import com.example.project_management_system.entity.Role;
import com.example.project_management_system.entity.Tenant;
import com.example.project_management_system.entity.Users;
import com.example.project_management_system.repository.RoleRepository;
import com.example.project_management_system.repository.TenantRepository;
import com.example.project_management_system.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class SignupService
{
    private final TenantRepository tenantRepo;
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;

    public SignupService(TenantRepository tenantRepo, UserRepository userRepo, RoleRepository roleRepo)
    {
        this.tenantRepo = tenantRepo;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
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
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        Users user = new Users(dto.getEmail(), encoder.encode(dto.getPassword()), createdTenant, createdRole);
        userRepo.saveAndFlush(user);

        return createdTenant;
    }


}