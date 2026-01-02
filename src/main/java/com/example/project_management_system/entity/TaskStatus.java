package com.example.project_management_system.entity;

import jakarta.persistence.*;

@Entity
public class TaskStatus
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    protected TaskStatus() {}
    public TaskStatus(Project project, String name)
    {
        this.project = project;
        this.name = name;
    }
}