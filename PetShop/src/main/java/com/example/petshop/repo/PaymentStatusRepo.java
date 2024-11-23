package com.example.petshop.repo;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.petshop.entity.PaymentStatus;

@Repository
public interface PaymentStatusRepo extends JpaRepository<PaymentStatus, Integer>{
	 
}
