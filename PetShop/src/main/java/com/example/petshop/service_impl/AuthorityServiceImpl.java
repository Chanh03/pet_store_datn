package com.example.petshop.service_impl;

import com.example.petshop.entity.Authority;
import com.example.petshop.repo.AuthorityRepo;
import com.example.petshop.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private AuthorityRepo authorityRepo;

    @Override
    public List<Authority> findAll() {
        return authorityRepo.findAll();
    }

    @Override
    public Authority create(Authority authority) {
        return authorityRepo.save(authority);
    }
}