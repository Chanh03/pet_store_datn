package com.example.petshop.service;

import com.example.petshop.entity.Authority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorityService {
    List<Authority> findAll();
    Authority create(Authority authority);
}