package com.example.project_management_system.controller;

import com.example.project_management_system.dto.StatusResponseDTO;
import com.example.project_management_system.repository.StatusRepository;
import com.example.project_management_system.service.StatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@RequestMapping("/api")
public class StatusController {
    private final StatusService statusService;
    public StatusController(StatusService statusService) { this.statusService = statusService; }

    @GetMapping("/status/fetch")
    public ResponseEntity<List<StatusResponseDTO>> getStatuses() {
        return ResponseEntity.ok(statusService.getStatuses());
    }
}
