package com.example.petshop.service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.petshop.entity.Role;

@Service
public interface RoleService {
    List<Role> findAll();
    Role findByName(String name);
    Role findById(String id);
}