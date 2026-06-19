package com.example.project_management_system.repository;

import com.example.project_management_system.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {}