package com.example.project_management_system.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.*;
import java.util.Date;

@Entity
public class Task
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private Users createdBy;

    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private Users assignedTo;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "due_date")
    private Date dueDate;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private TaskCategory category;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    private Priority priority = Priority.MEDIUM;


    protected Task() {}

    public Task(String title, String description, Users createdBy, Users assignedTo,
                Instant createdAt, Date dueDate, Project project, TaskCategory category,
                TaskStatus status, Priority priority) {
        this.title = title;
        this.description = description;
        this.createdBy = createdBy;
        this.assignedTo = assignedTo;
        this.createdAt = createdAt;
        this.dueDate = dueDate;
        this.project = project;
        this.category = category;
        this.status = status;
        this.priority = priority;
    }
}