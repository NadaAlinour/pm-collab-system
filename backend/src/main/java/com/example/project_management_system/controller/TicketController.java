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

    @GetMapping("/tickets/get/{projectId}")
    public ResponseEntity<List<TicketResponseDTO>> getTicketsByTenant(@PathVariable Long projectId) {
        return ResponseEntity.ok(ticketService.getTickets(projectId));
    }

    @GetMapping("/ticket/get/{id}")
    public ResponseEntity<TicketResponseDTO> getTicketById(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    @PostMapping("/tickets/create")
    public ResponseEntity<TicketResponseDTO> createTicket(@RequestBody TicketRequestDTO dto)
    {
        return ResponseEntity.ok((ticketService.saveTicket(dto)));
    }


}
