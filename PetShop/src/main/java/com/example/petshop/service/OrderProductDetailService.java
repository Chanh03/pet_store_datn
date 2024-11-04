package com.example.petshop.service;


import com.example.petshop.entity.OrderProductDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderProductDetailService {
    List<OrderProductDetail> findAll();
    OrderProductDetail findById(Integer id);
    OrderProductDetail save(OrderProductDetail orderProductDetail);
}
