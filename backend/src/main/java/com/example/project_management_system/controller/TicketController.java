package com.example.project_management_system.controller;


import com.example.project_management_system.dto.ProjectRequestDTO;
import com.example.project_management_system.dto.ProjectResponseDTO;
import com.example.project_management_system.dto.TicketRequestDTO;
import com.example.project_management_system.dto.TicketResponseDTO;
import com.example.project_management_system.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/tenants/{id}/projects/{pid}/tickets/get/{projectId}")
    public ResponseEntity<List<TicketResponseDTO>> getTicketsByTenant(@PathVariable Long projectId) {
        return ResponseEntity.ok(ticketService.getTickets(projectId));
    }

    @PostMapping("/tenants/{id}/projects/{pid}/tickets/create")
    public ResponseEntity<TicketResponseDTO> createTicket(@RequestBody TicketRequestDTO dto)
    {
        return ResponseEntity.ok((ticketService.saveTicket(dto)));
    }


}
