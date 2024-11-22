package com.example.petshop.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.petshop.entity.PaymentStatus;

@Service
public interface PaymentStatusService {

    PaymentStatus create(PaymentStatus paymentStatus);

    void update(PaymentStatus paymentStatus);

    PaymentStatus findById(int id);
}
