package com.example.project_management_system.controller;

import com.example.project_management_system.entity.Role;
import com.example.project_management_system.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/role")
public class RoleController
{
    private final RoleService roleService;

    public RoleController(RoleService roleService)
    {
        this.roleService = roleService;
    }

    @PostMapping("/add")
    public ResponseEntity<Role> addRole(@RequestBody Role role)
    {
        return ResponseEntity.ok(roleService.addRole(role));
    }

    /*
    @PostMapping("/update/{roleId}")
    public ResponseEntity<Role> updateRole(@PathVariable Long roleId, String roleName)
    {
        return ResponseEntity.ok(roleService.updateRole(roleId, roleName));
    }*/

    @DeleteMapping("/delete")
    public ResponseEntity<Role> deleteRole(@RequestBody Role role)
    {
        return ResponseEntity.ok(roleService.deleteRole(role));
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles()
    {
        return ResponseEntity.ok(roleService.getRoles());
    }



}