package com.example.petshop.service;

import com.example.petshop.entity.OrderStatus;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public interface OrderStatusService {
    OrderStatus getById(Integer orderStatusID);
}
