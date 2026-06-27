package com.example.project_management_system.service;

import com.example.project_management_system.Mapper;
import com.example.project_management_system.dto.*;
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

    private final Mapper mapper;


    public TicketService(Mapper mapper, TicketRepository ticketRepo, ProjectRepository projectRepo, StatusRepository statusRepo, CategoryRepository categoryRepo, UserRepository userRepo)
    {
        this.mapper = mapper;
        this.ticketRepo = ticketRepo;
        this.projectRepo = projectRepo;
        this.statusRepo = statusRepo;
        this.categoryRepo = categoryRepo;
        this.userRepo = userRepo;
    }

    // have one method for ticket by id, and another for tickets with params (filters etc)
    public TicketResponseDTO getTicketById(Long id) {
        Ticket t = ticketRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        return mapper.toTicketResponse(t);
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
                status = new StatusResponseDTO(t.getStatus().getId(), t.getStatus().getValue(), t.getStatus().getName());


            CategoryResponseDTO category = null;
            if (t.getCategory() != null)
                category = new CategoryResponseDTO(t.getCategory().getId(), t.getCategory().getName());

            UserResponseDTO createdBy = new UserResponseDTO(
                    t.getCreatedBy().getId(), t.getCreatedBy().getFirstname(), t.getCreatedBy().getLastname(), t.getCreatedBy().getEmail(), t.getCreatedBy().getTenant().getId(), t.getCreatedBy().getRole().getId()
            );

            UserResponseDTO assignee = null;
            if (t.getAssignedTo() != null)
                    assignee = new UserResponseDTO(t.getAssignedTo().getId(), t.getAssignedTo().getFirstname(),
                            t.getAssignedTo().getLastname(), t.getAssignedTo().getEmail(), t.getAssignedTo().getTenant().getId(), t.getAssignedTo().getRole().getId());


            return new TicketResponseDTO(t.getId(), t.getProject().getId(), t.getTitle(), t.getDescription(),
                    status, category, t.getPriority(),
                    t.getDueDate(), createdBy, assignee);
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

        // assigned to
        Users assignee = null;
        if(dto.getAssigneeId() != null) {
            assignee = userRepo.findById(dto.getAssigneeId())
                    .orElseThrow(() -> new RuntimeException(("User not found")));
            reqTicket.setAssignedTo(assignee);

        }


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
            statusRes.setValue(ticket.getStatus().getValue());
        }

        CategoryResponseDTO categoryRes = null;
        if (ticket.getCategory() != null) {
            categoryRes = new CategoryResponseDTO();
            categoryRes.setId(ticket.getCategory().getId());
            categoryRes.setName(ticket.getCategory().getName());
        }

        // user response dto
        UserResponseDTO createdBy = new UserResponseDTO(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getTenant().getId(), user.getRole().getId());

        UserResponseDTO assignedTo = null;
        if (assignee != null) {
            assignedTo = new UserResponseDTO(assignee.getId(), assignee.getFirstname(), assignee.getLastname(), assignee.getEmail(), assignee.getTenant().getId(), assignee.getRole().getId());
        }

        TicketResponseDTO resTicket = new TicketResponseDTO(ticket.getId(), ticket.getProject().getId(), ticket.getTitle(), ticket.getDescription(), statusRes, categoryRes, ticket.getPriority(), ticket.getDueDate(), createdBy, assignedTo);

        return resTicket;

    }
}
