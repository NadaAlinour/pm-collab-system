package com.example.project_management_system.service;

import com.example.project_management_system.dto.UserResponseDTO;
import com.example.project_management_system.dto.UserSignupDTO;
import com.example.project_management_system.entity.Users;
import com.example.project_management_system.repository.RoleRepository;
import com.example.project_management_system.repository.TenantRepository;
import com.example.project_management_system.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService
{
    private final TenantRepository tenantRepo;
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;

    private final PasswordEncoder passwordEncoder;

    public UserService(TenantRepository tenantRepo, UserRepository userRepo, RoleRepository roleRepo, PasswordEncoder passwordEncoder)
    {
        this.tenantRepo = tenantRepo;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }


    public UserResponseDTO signup(UserSignupDTO dto)
    {
        Users user = new Users(dto.getFirstname(), dto.getLastname(), dto.getEmail(), passwordEncoder.encode(dto.getPassword()), tenantRepo.getReferenceById(1L), roleRepo.getReferenceById(1L));
        userRepo.saveAndFlush(user);
        return new UserResponseDTO(user.getId(),dto.getFirstname(), dto.getLastname(), dto.getEmail(), 1L, 1L);
    }


}