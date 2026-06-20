package com.example.project_management_system.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Status
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String value;

    @Column(nullable = false)
    private String name;


    public Status() {}
    public Status(String value, String name)
    {
        this.value = value; this.name = name;
    }
}