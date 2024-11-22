package com.example.petshop.service_impl;

import com.example.petshop.entity.Order;
import com.example.petshop.entity.PaymentStatus;
import com.example.petshop.repo.OrderPayMentRepo;
import com.example.petshop.service.OrderPayMentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderPayMentServiceImpl implements OrderPayMentService {
    @Autowired
    private OrderPayMentRepo orderPayMentRepo;


    @Override
    public PaymentStatus findById(Integer payMent) {

        return orderPayMentRepo.findById(payMent).orElse(null);
    }

    @Override
    public List<PaymentStatus> findAll() {
        return orderPayMentRepo.findAll();
    }

    @Override
    public PaymentStatus getById(int i) {
        return orderPayMentRepo.findById(i).orElse(null);
    }
}
