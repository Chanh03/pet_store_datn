package com.example.petshop.service_impl;

import com.example.petshop.entity.Authority;
import com.example.petshop.entity.User;
import com.example.petshop.repo.AuthorityRepo;
import com.example.petshop.repo.UserRepo;
import com.example.petshop.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private AuthorityRepo authorityRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<Authority> findAll() {
        return authorityRepo.findAll();
    }

}
