package com.example.project_management_system.repository;

import com.example.project_management_system.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Ticket, Long> {}