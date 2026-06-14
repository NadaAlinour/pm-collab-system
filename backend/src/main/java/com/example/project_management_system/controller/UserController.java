package com.example.project_management_system.controller;

import com.example.project_management_system.dto.UserResponseDTO;
import com.example.project_management_system.dto.UserSignupDTO;
import com.example.project_management_system.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/api/user")

public class UserController
{
    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> signupUser(@RequestBody UserSignupDTO dto)
    {
        return ResponseEntity.ok((userService.signup(dto)));
    }

}