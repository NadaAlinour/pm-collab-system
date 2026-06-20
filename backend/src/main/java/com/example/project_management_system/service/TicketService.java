package com.example.project_management_system.service;

import com.example.project_management_system.dto.CategoryResponseDTO;
import com.example.project_management_system.dto.StatusResponseDTO;
import com.example.project_management_system.dto.TicketRequestDTO;
import com.example.project_management_system.dto.TicketResponseDTO;
import com.example.project_management_system.entity.*;
import com.example.project_management_system.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepo;
    private final ProjectRepository projectRepo;
    private final StatusRepository statusRepo;
    private final CategoryRepository categoryRepo;
    private final UserRepository userRepo;


    public TicketService(TicketRepository ticketRepo, ProjectRepository projectRepo, StatusRepository statusRepo, CategoryRepository categoryRepo, UserRepository userRepo)
    {
        this.ticketRepo = ticketRepo;
        this.projectRepo = projectRepo;
        this.statusRepo = statusRepo;
        this.categoryRepo = categoryRepo;
        this.userRepo = userRepo;
    }

    // fetch all tickets (for now, later on filter and get by id)
    // huh no i wanna get tickets by tenant id or all tickets or with filters lol
    public List<TicketResponseDTO> getTickets(Long id) {
        // will be returned as list of type ticket probably
        List<Ticket> tickets = ticketRepo.findAllByProjectId(id);

        // map tickets to list of TicketResponseDTO
        List<TicketResponseDTO> ticketsRes = tickets.stream().map(t -> {
            StatusResponseDTO status = null;
            if (t.getStatus() != null)
                status = new StatusResponseDTO(t.getStatus().getId(), t.getStatus().getName());


            CategoryResponseDTO category = null;
            if (t.getCategory() != null)
                category = new CategoryResponseDTO(t.getCategory().getId(), t.getCategory().getName());


            return new TicketResponseDTO(t.getId(), t.getProject().getId(), t.getTitle(), t.getDescription(),
                    status, category, t.getPriority(),
                    t.getDueDate(), t.getCreatedBy().getId());
        }).toList();

        return ticketsRes;

    }





    // create ticket
    public TicketResponseDTO saveTicket(TicketRequestDTO dto) {
        // map dto to ticket entity
        Ticket reqTicket = new Ticket();

        // project
        Project project = projectRepo.findById(dto.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        reqTicket.setProject(project);

        // status from status id
        if (dto.getStatusId() != null) {
            Status status = statusRepo.findById(dto.getStatusId())
                    .orElseThrow(() -> new RuntimeException("Status not found"));

            reqTicket.setStatus(status);
        }


        // category from category id
        if (dto.getCategoryId() != null) {
            Category category = categoryRepo.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));

            reqTicket.setCategory(category);
        }


        // created by
        Users user = userRepo.findById(dto.getCreatedBy())
                        .orElseThrow(() -> new RuntimeException("User not found"));

        reqTicket.setCreatedBy(user);

        reqTicket.setTitle((dto.getTitle()));
        reqTicket.setDescription(dto.getDescription());
        reqTicket.setDueDate(dto.getDueDate());

        // priority
        reqTicket.setPriority(dto.getPriority());

        Ticket ticket = ticketRepo.saveAndFlush(reqTicket);


        StatusResponseDTO statusRes = null;
        if (ticket.getStatus() != null) {
            statusRes = new StatusResponseDTO();
            statusRes.setId(ticket.getStatus().getId());
            statusRes.setName(ticket.getStatus().getName());
        }

        CategoryResponseDTO categoryRes = null;
        if (ticket.getCategory() != null) {
            categoryRes = new CategoryResponseDTO();
            categoryRes.setId(ticket.getCategory().getId());
            categoryRes.setName(ticket.getCategory().getName());
        }


        TicketResponseDTO resTicket = new TicketResponseDTO(ticket.getId(), ticket.getProject().getId(), ticket.getTitle(), ticket.getDescription(), statusRes, categoryRes, ticket.getPriority(), ticket.getDueDate(), ticket.getCreatedBy().getId());

        return resTicket;

    }
}
