package com.example.petshop.service;
import com.example.petshop.entity.User;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDetails findById(String username);
    User findByToken(String token);
    List<User> findAll();
    User findByUsername(String name);
    User create(User user);
    void update(User user);
    boolean existedByUsername(String username);
    boolean existedByEmail(String emai);

}