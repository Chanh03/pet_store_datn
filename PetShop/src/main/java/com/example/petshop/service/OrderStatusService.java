package com.example.petshop.service;

import com.example.petshop.entity.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderStatusService {
    OrderStatus findById(Integer orderStatus);

    List<OrderStatus> findAll();

    OrderStatus getByStatus(Integer orderStatus);

    OrderStatus getById(int i);

    OrderStatus create(OrderStatus orderStatus);

    void update(OrderStatus orderStatus);

    OrderStatus findById(int id);
}
