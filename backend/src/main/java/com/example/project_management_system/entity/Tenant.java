package com.example.project_management_system.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
public class Tenant
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "tenant")
    private List<Users> users = new ArrayList<>();

    @OneToMany(mappedBy = "tenant")
    private List<Project> projects = new ArrayList<>();


    public Tenant() {}
    public Tenant(String name)
    {
        this.name = name;
    }

}