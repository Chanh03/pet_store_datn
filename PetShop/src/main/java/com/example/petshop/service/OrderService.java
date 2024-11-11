package com.example.petshop.service;

import com.example.petshop.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderService {
    List<Order> getAll();

    Order save(Order order);

    void deleteById(Integer id);

    List<Order> getHistory(String username);

    Order getByOrderId(Integer id);
}
