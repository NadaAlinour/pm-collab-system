package com.example.project_management_system.dto;
import com.example.project_management_system.entity.Priority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketRequestDTO {
    private Long projectId;
    private String title;
    private String description;
    private Long statusId;
    private Long categoryId;
    private Priority priority;
    private LocalDate dueDate;
    private Long createdBy;
    private Long assigneeId;
}
