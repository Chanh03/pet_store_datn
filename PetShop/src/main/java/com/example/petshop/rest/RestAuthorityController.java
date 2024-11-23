package com.example.petshop.rest;

import com.example.petshop.entity.Authority;
import com.example.petshop.entity.Role;
import com.example.petshop.service.AuthorityService;
import com.example.petshop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/authority")
@RestController
@CrossOrigin("*")
public class RestAuthorityController {
    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<Authority> getAuthority() {
        return authorityService.findAll();
    }

    @PutMapping("/{authorityId}/{role}")
    public Authority updateRole(@PathVariable("authorityId") int authorityId, @PathVariable("role") String role) {
        Role role1 = roleService.findByRole(role);
        Authority authority = authorityService.findById(authorityId);
        authority.setRole(role1);
        return authorityService.save(authority);
    }
}
