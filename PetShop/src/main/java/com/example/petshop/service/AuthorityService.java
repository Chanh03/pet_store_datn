package com.example.petshop.service;

import com.example.petshop.entity.Authority;
import com.example.petshop.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorityService {
    List<Authority> findAll();
    Authority create(Authority authority);

    Authority findById(int authorityId);

    Authority save(Authority authority);

    void deleteByUserName(User user);
}