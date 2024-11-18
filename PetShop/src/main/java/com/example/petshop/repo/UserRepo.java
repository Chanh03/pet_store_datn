package com.example.petshop.repo;

import com.example.petshop.entity.User;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
    @Query("SELECT u FROM User u WHERE u.userName = :username")
    User findByUsername(String username);
    @Query("SELECT u FROM User u WHERE u.activeToken = :token")
    User findByToken(String token);
    @Query("SELECT u FROM User u WHERE u.TemporaryGUID = :temp_token")
    User findByTempToken(String temp_token);
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM User u WHERE u.email = :email")
    boolean existsByEmail(@Param("email") String email);
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM User u WHERE u.TemporaryGUID = :temp_token")
    boolean existsByTempToken(@Param("temp_token") String temp_token);
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM User u WHERE u.userName = :userName")
    boolean existsByUsername(@Param("userName") String userName);
    @Query("SELECT u FROM User u WHERE u.isDelete = true")
    List<User> findUserByIsDeleteTrue(); 
    @Query("SELECT u FROM User u WHERE u.email = :email")
    List<User> findByEmail(String email);
}