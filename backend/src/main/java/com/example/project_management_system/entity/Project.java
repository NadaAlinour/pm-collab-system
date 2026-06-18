package com.example.project_management_system.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
public class Project
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    @ManyToMany(mappedBy = "projects")
    private Set<Users> users = new HashSet<>();

    @OneToMany(mappedBy = "project")
    private List<Ticket> tickets = new ArrayList<>();


    public Project() {}
    public Project(String name, Tenant tenant)
    {
        this.name = name;
        this.tenant = tenant;
    }
}