package com.example.petshop.controller;

import com.example.petshop.entity.Authority;
import com.example.petshop.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/authority")
@RestController
@CrossOrigin("*")
public class AuthorityController {
    @Autowired
    private AuthorityService authorityService;

    @GetMapping
    public List<Authority> getAuthority() {
        return authorityService.findAll();
    }

    @PutMapping("/{authorityId}/{role}")
    public Authority updateRole(@PathVariable int authorityId, @PathVariable String role) {
        Authority authority = authorityService.findById(authorityId);
        authority.getRole().setName(role);
        return authorityService.save(authority);
    }
}
