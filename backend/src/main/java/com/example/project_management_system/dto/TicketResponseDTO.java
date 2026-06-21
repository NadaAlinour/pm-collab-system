package com.example.project_management_system.dto;
import com.example.project_management_system.entity.Priority;
import com.example.project_management_system.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponseDTO {
    private Long id;
    private Long projectId;
    private String title;
    private String description;
    private StatusResponseDTO status;
    private CategoryResponseDTO category;
    private Priority priority;
    private LocalDate dueDate;
    private UserResponseDTO createdBy;
    private UserResponseDTO assignee;
}
