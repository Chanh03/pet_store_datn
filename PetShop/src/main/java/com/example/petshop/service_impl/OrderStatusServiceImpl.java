package com.example.petshop.service_impl;

import com.example.petshop.entity.OrderStatus;
import com.example.petshop.repo.OrderStatusRepo;
import com.example.petshop.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {
    @Autowired
    private OrderStatusRepo orderStatusRepo;

    @Override
    public OrderStatus findById(Integer orderStatus) {
        return orderStatusRepo.findById(orderStatus).orElse(null);
    }

    @Override
    public List<OrderStatus> findAll() {
        return orderStatusRepo.findAll();
    }

    @Override
    public OrderStatus getByStatus(Integer orderStatus) {
        return orderStatusRepo.findById(orderStatus).orElse(null);
    }

    @Override
    public OrderStatus getById(int i) {
        return orderStatusRepo.findById(i).orElse(null);
    }

    @Override
    public OrderStatus create(OrderStatus orderStatus) {
        return orderStatusRepo.save(orderStatus);
    }

    @Override
    public void update(OrderStatus orderStatus) {
        orderStatusRepo.save(orderStatus);
    }

    @Override
    public OrderStatus findById(int id) {
        return orderStatusRepo.findById(id).orElse(null);
    }
}
