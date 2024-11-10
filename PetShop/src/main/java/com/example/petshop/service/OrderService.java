package com.example.petshop.service;

import com.example.petshop.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    List<Order> getAll();

    Order getById(Integer id);

    Order save(Order order);

    void deleteById(Integer id);

    List<Order> getHistory(String username);

    Order getByOrderId(Integer id);
}
