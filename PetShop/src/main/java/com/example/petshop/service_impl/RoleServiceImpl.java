package com.example.petshop.service_impl;

import com.example.petshop.entity.Role;
import com.example.petshop.repo.RoleRepo;
import com.example.petshop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepo roleRepo;

    @Override
    public Role findRoleByName(String roleUser) {
        return roleRepo.findRoleByName(roleUser);
    }

    @Override
    public List<Role> getAll() {
        return roleRepo.findAll();
    }
}
