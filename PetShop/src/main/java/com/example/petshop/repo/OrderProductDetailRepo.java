package com.example.petshop.repo;

import com.example.petshop.entity.Order;
import com.example.petshop.entity.OrderProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductDetailRepo extends JpaRepository<OrderProductDetail, Integer> {
    List<OrderProductDetail> findByOrderID(Order order);
}
