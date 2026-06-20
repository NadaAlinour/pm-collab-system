package com.example.project_management_system.repository;

import com.example.project_management_system.entity.Project;
import com.example.project_management_system.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    public List<Ticket> findAllByProjectId(Long id);

}