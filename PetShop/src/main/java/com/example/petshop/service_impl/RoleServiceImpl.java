package com.example.petshop.service_impl;

import com.example.petshop.entity.Role;
import com.example.petshop.repo.RoleRepo;
import com.example.petshop.service.RoleService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepo roleRepo;

    @Override
    public List<Role> findAll() {
        return roleRepo.findAll();
    }

    @Override
    public Role findByName(String name) {
        return null;
    }

    @Override
    public Role findById(String id) {
        return roleRepo.findById(id).get();
    }
}