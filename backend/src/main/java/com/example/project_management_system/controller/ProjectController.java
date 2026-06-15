package com.example.project_management_system.controller;

import com.example.project_management_system.dto.ProjectResponseDTO;
import com.example.project_management_system.entity.Project;
import com.example.project_management_system.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/tenants/{id}/projects")
    public ResponseEntity<List<ProjectResponseDTO>> getProjectsByTenant(@PathVariable Long id)
    {
        return ResponseEntity.ok((projectService.getProjectsByTenant(id)));
    }


}
