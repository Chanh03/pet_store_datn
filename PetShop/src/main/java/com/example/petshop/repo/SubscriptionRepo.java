package com.example.petshop.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.petshop.entity.Subscription;

import java.util.Optional;

public interface SubscriptionRepo extends JpaRepository<Subscription, Long> {
    Optional<Subscription> findByEmail(String email);
}