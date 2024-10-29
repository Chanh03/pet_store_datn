package com.example.petshop.service;

import com.example.petshop.entity.Authority;
import com.example.petshop.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface RoleService {
    Role findRoleByName(String roleUser);

    List<Role> getAll();

    Set<Authority> getRoleByName(String role);

    Role findByRole(String role);
}
