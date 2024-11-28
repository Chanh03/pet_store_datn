package com.example.petshop.service;

import com.example.petshop.entity.Order;
import com.example.petshop.entity.PaymentStatus;
import com.example.petshop.repo.OrderStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderPayMentService{

    PaymentStatus findById(Integer payMent);

    List<PaymentStatus> findAll();

    PaymentStatus getById(int i);
}
