package com.example.petshop.repo;

import com.example.petshop.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, String> {
    Role findRoleByName(String roleUser);
}
