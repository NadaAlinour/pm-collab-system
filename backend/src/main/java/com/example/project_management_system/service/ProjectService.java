package com.example.project_management_system.service;
import com.example.project_management_system.dto.ProjectRequestDTO;
import com.example.project_management_system.dto.ProjectResponseDTO;
import com.example.project_management_system.entity.Project;
import com.example.project_management_system.entity.Tenant;
import com.example.project_management_system.repository.ProjectRepository;

import com.example.project_management_system.repository.TenantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepo;
    private final TenantRepository tenantRepo;

    public ProjectService(ProjectRepository projectRepo, TenantRepository tenantRepo)
    {
        this.projectRepo = projectRepo;
        this.tenantRepo = tenantRepo;
    }

    // fetch list of projects by tenant id
    public List<ProjectResponseDTO> getProjectsByTenant(Long tenant_id) {
        List<Project> projects = projectRepo.findAllByTenantId(tenant_id);

        return projects.stream()
                .map(p -> new ProjectResponseDTO(p.getId(), p.getName()))
                .toList();
    }


    public ProjectResponseDTO saveProject(ProjectRequestDTO dto) {

        Project reqProject = new Project();
        reqProject.setName(dto.getName());

        // get tenant
        Tenant tenant = tenantRepo.findById(dto.getTenantId())
                .orElseThrow(() -> new RuntimeException("Tenant not found"));

        reqProject.setTenant(tenant);

        // save project
        Project project = projectRepo.saveAndFlush(reqProject);

        // map entity to response dto
        ProjectResponseDTO resProject = new ProjectResponseDTO();
        resProject.setId(project.getId());
        resProject.setName(project.getName());

        return resProject;
    }


}
