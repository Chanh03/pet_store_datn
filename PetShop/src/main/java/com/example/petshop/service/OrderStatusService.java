package com.example.petshop.service;

import com.example.petshop.entity.OrderStatus;
import org.springframework.stereotype.Service;

@Service
public interface OrderStatusService {
    OrderStatus findById(Integer orderStatus);
}
