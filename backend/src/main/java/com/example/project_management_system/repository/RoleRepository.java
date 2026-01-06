package com.example.project_management_system.repository;

import com.example.project_management_system.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByTenant(Long tenantId);
}