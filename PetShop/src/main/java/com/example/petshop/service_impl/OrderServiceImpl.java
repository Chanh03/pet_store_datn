package com.example.petshop.service_impl;

import com.example.petshop.entity.Order;
import com.example.petshop.entity.User;
import com.example.petshop.repo.OrderRepo;
import com.example.petshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
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

    @Override
    public Order getByOrderIdAndUser(int id, User user) {
        return orderRepo.findByIdAndUser(id, user);
    }

    @Override
    public Order getById(Integer id) {
        return orderRepo.findById(id).orElse(null);
    }

    @Override
    public List<Order> findOrdersByDate(Date from, Date to) {
        return orderRepo.findByOrderDate(from, to);
    }

}
