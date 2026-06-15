package com.example.project_management_system.service;
import com.example.project_management_system.dto.ProjectResponseDTO;
import com.example.project_management_system.entity.Project;
import com.example.project_management_system.repository.ProjectRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepo;

    public ProjectService(ProjectRepository projectRepo) {
        this.projectRepo = projectRepo;
    }

    // fetch list of projects by tenant id
    // add filtering later!
    public List<ProjectResponseDTO> getProjectsByTenant(Long tenant_id) {
        List<Project> projects = projectRepo.findAllByTenantId(tenant_id);

        return projects.stream()
                .map(p -> new ProjectResponseDTO(p.getId(), p.getName(), tenant_id))
                .toList();
    }
}
