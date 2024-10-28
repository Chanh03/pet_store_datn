package com.example.petshop.service;

import com.example.petshop.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    Role findRoleByName(String roleUser);

    List<Role> getAll();
}
