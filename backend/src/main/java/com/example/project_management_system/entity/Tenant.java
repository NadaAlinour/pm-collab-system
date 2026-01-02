package com.example.project_management_system.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Tenant
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(mappedBy = "tenant")
    private List<Users> users = new ArrayList<>();

    @OneToMany(mappedBy = "tenant")
    private List<Project> projects = new ArrayList<>();


    protected Tenant() {}
    public Tenant(String name)
    {
        this.name = name;
    }

}