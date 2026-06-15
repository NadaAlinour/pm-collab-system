package com.example.project_management_system.repository;

import com.example.project_management_system.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    public List<Project> findAllByTenantId(Long id);
}