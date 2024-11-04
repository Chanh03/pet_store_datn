package com.example.petshop.service;

import com.example.petshop.entity.Authority;
import com.example.petshop.entity.User;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorityService {
    List<Authority> findAll();
    Authority create(Authority authority);
    void deleteByUserName(User userName);
}