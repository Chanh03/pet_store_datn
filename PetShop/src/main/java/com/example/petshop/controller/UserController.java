package com.example.petshop.controller;

import com.example.petshop.entity.Authority;
import com.example.petshop.entity.User;
import com.example.petshop.service.AuthorityService;
import com.example.petshop.service.RoleService;
import com.example.petshop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{id}")
    public void updateUser(@PathVariable String id, @RequestBody User user) {
        User user1 = userService.getUserById(id);
        user.setUserName(user1.getUsername());
        user.setDateCreated(user1.getDateCreated());
        user.setActiveToken(user1.getActiveToken());
        user.setUserPassword(user1.getUserPassword());
        userService.updateUser(user);
    }

}
