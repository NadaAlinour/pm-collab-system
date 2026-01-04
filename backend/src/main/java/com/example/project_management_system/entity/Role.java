package com.example.project_management_system.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Role
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    @OneToMany(mappedBy = "role")
    private List<Users> users = new ArrayList<>();

    protected Role() {}
    public Role(Tenant tenant, String name)
   {
       this.tenant = tenant;
       this.name = name;
   }
}