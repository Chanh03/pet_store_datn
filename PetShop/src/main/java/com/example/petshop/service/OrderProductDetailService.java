package com.example.petshop.service;

import com.example.petshop.entity.Order;
import com.example.petshop.entity.OrderProductDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderProductDetailService {
    List<OrderProductDetail> getAll();

    List<OrderProductDetail> getByOrderID(Order order);

    OrderProductDetail save(OrderProductDetail orderProductDetail);
}
