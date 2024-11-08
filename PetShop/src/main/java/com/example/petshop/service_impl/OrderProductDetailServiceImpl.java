package com.example.petshop.service_impl;

import com.example.petshop.entity.OrderProductDetail;
import com.example.petshop.repo.OrderProductDetailRepo;
import com.example.petshop.service.OrderProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProductDetailServiceImpl implements OrderProductDetailService {
    @Autowired
    private OrderProductDetailRepo orderProductDetailRepo;

    @Override
    public List<OrderProductDetail> findAll() {
        return orderProductDetailRepo.findAll();
    }

    @Override
    public void findById(Integer id) {
         orderProductDetailRepo.findById(id).orElse(null);
    }

    @Override
    public OrderProductDetail save(OrderProductDetail orderProductDetail) {
        return orderProductDetailRepo.save(orderProductDetail);
    }
}
