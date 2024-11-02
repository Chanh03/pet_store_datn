package com.example.petshop.service;

import com.example.petshop.entity.PaymentStatus;
import com.example.petshop.repo.OrderStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface OrderPayMentService{

    PaymentStatus findById(Integer payMent);
}
