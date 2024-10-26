
package com.example.petshop.service_impl;


import com.example.petshop.entity.User;
import com.example.petshop.repo.UserRepo;
import com.example.petshop.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails findById(String username) {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return user;
    }

    @Override
    public User findByUsername(String name) {
        return userRepo.findByUsername(name);
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User create(User user) {
        return userRepo.save(user);
    }

    @Override
    public void update(User user) {
        userRepo.save(user);
    }

    @Override
    public boolean existedByUsername(String username) {
        return userRepo.existsById(username);
    }

    @Override
    public User findByToken(String token) {
        User user  = userRepo.findByToken(token);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with token: " + token);
        }
        return user;
    }

    @Override
    public boolean existedByEmail(String emai) {
        return userRepo.existsByEmail(emai);
    }
}
