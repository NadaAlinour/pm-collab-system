package com.example.project_management_system.repository;

import com.example.project_management_system.entity.TicketCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskCategoryRepository extends JpaRepository<TicketCategory, Long> {}