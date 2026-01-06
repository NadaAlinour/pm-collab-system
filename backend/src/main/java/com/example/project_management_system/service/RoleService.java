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

    // what happens if db fails? what message gets returned?
    // add role, with perms later
    public Role addRole(Role role)
    {
        return roleRepo.saveAndFlush(role);
    }

    // update role
    public Role updateRole(Role role)
    {
        return roleRepo.save(role);
    }


    // delete role
    public void deleteRole(Role role)
    {
        roleRepo.delete(role);
    }

    // get all roles by tenant id
    public List<Role> getRoles(Long tenantId)
    {
        return roleRepo.findByTenant(tenantId);
    }

}