package com.example.petshop.service_impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.petshop.entity.PaymentStatus;
import com.example.petshop.repo.PaymentStatusRepo;
import com.example.petshop.service.PaymentStatusService;

@Service
public class PaymentStatusServiceImpl implements PaymentStatusService {

    @Autowired
    private PaymentStatusRepo paymentStatusRepo;

    @Override
    public PaymentStatus create(PaymentStatus paymentStatus) {
        return paymentStatusRepo.save(paymentStatus);
    }

    @Override
    public void update(PaymentStatus paymentStatus) {
        paymentStatusRepo.save(paymentStatus);

    }

    @Override
    public PaymentStatus findById(int id) {
        return paymentStatusRepo.findById(id).orElse(null);
    }
}
