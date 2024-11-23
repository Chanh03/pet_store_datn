package com.example.petshop.rest;

import com.example.petshop.entity.Role;
import com.example.petshop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/role")
public class RestRoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<Role> getRole() {
        return roleService.getAll();
    }
}