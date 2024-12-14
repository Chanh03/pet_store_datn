package com.example.petshop.service_impl;

import com.example.petshop.entity.Order;
import com.example.petshop.entity.OrderProductDetail;
import com.example.petshop.entity.Product;
import com.example.petshop.entity.User;
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
    public List<OrderProductDetail> getAll() {
        return orderProductDetailRepo.findAll();
    }

    @Override
    public List<OrderProductDetail> getByOrderID(Order order) {
        return orderProductDetailRepo.findByOrderID(order);
    }

    @Override
    public OrderProductDetail save(OrderProductDetail orderProductDetail) {
        return orderProductDetailRepo.save(orderProductDetail);
    }

    @Override
    public List<OrderProductDetail> getByOrderIdAndUser(Order order, User user) {
        return orderProductDetailRepo.findByOrderIDAndUser(order , user);
    }

    @Override
    public OrderProductDetail findByOrderIDAndProductID(Order orderID, Product productID) {

        return orderProductDetailRepo.findByOrderIDAndProductID(orderID, productID);
    }

    @Override
    public List<Object[]> getTop5Product(List<Order> order) {
        return orderProductDetailRepo.findTop5Product(order);
    }
}