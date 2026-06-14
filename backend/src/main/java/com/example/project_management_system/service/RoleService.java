package com.example.project_management_system.service;

import com.example.project_management_system.entity.Role;
import com.example.project_management_system.entity.Tenant;
import com.example.project_management_system.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService
{
    private final RoleRepository roleRepo;

    public RoleService(RoleRepository roleRepo)
    {
        this.roleRepo = roleRepo;
    }

    public Role addRole(Role role)
    {
        return roleRepo.saveAndFlush(role);
    }

    // update role
    /*
    public Role updateRole(Long roleId, String roleName)
    {
        Role existingRole = roleRepo.findById(roleId).orElseThrow(() -> new RuntimeException("role not found"));
        existingRole.setName(roleName);
        return roleRepo.save(existingRole);
    }
    */


    // delete role
    public Role deleteRole(Role role)
    {
        roleRepo.delete(role);
        return role;
    }

    // get all roles
    public List<Role> getRoles()
    {
        return roleRepo.findAll();
    }

}