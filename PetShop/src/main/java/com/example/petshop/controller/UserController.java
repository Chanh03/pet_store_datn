package com.example.petshop.controller;

import com.example.petshop.entity.User;
import com.example.petshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUser() {
        return userService.getAll();
    }
}
