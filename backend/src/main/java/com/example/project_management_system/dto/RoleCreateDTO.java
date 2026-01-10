package com.example.project_management_system.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RoleCreateDTO {
    private String name;
    private Long tenantId;
}
