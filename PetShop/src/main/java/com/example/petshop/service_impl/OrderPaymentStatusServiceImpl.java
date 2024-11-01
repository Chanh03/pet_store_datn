package com.example.petshop.service_impl;

import com.example.petshop.entity.PaymentStatus;
import com.example.petshop.repo.OrderPaymentRepo;
import com.example.petshop.service.OrderPaymentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderPaymentStatusServiceImpl implements OrderPaymentStatusService {
    @Autowired
    private OrderPaymentRepo orderPaymentRepo;

    @Override
    public PaymentStatus getByStatusPayment(String orderPaymentStatusID) {
        return orderPaymentRepo.getByStatusPayment(orderPaymentStatusID);
    }
}
