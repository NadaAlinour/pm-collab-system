package com.example.project_management_system.service;

import com.example.project_management_system.dto.StatusRequestDTO;
import com.example.project_management_system.dto.StatusResponseDTO;
import com.example.project_management_system.entity.Status;
import com.example.project_management_system.repository.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {
    private final StatusRepository statusRepo;

    public StatusService(StatusRepository statusRepo) { this.statusRepo = statusRepo; }

    // fetch all statuses
    public List<StatusResponseDTO> getStatuses() {
        List<Status> statuses = statusRepo.findAll();

        // map status entity to status response dto
        return statuses.stream().map(s -> new StatusResponseDTO(s.getId(), s.getValue(), s.getName())).toList();
    }
}
