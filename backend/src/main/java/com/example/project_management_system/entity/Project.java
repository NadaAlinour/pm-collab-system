package com.example.project_management_system.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Project
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    @ManyToMany(mappedBy = "projects")
    private Set<Users> users = new HashSet<>();

    @OneToMany(mappedBy = "project")
    private List<Task> tasks = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    private List<TaskStatus> statuses = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    private List<TaskCategory> categories = new ArrayList<>();

    protected Project() {}
    public Project(String name, Tenant tenant)
    {
        this.name = name;
        this.tenant = tenant;
    }
}