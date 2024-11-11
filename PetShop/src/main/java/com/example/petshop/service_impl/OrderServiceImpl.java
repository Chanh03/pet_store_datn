package com.example.petshop.service_impl;

import com.example.petshop.entity.Order;
import com.example.petshop.repo.OrderRepo;
import com.example.petshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Override
    public List<Order> getAll() {
        return orderRepo.findAll();
    }

    @Override
    public Order save(Order order) {
        return orderRepo.save(order);
    }

    @Override
    public void deleteById(Integer id) {
        orderRepo.deleteById(id);
    }

    @Override
    public List<Order> getHistory(String username) {
        return orderRepo.findByUserName(username);
    }

    @Override
    public Order getByOrderId(Integer id) {
        return orderRepo.findById(id).orElse(null);
    }
}
