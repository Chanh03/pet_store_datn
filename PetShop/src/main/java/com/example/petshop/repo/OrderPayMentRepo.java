package com.example.petshop.repo;

import com.example.petshop.entity.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPayMentRepo extends JpaRepository<PaymentStatus, Integer> {
}
