package com.example.petshop.service;

import com.example.petshop.entity.PaymentStatus;
import com.example.petshop.entity.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public interface OrderPaymentStatusService {
    PaymentStatus getByStatusPayment(String orderPaymentStatusID);
}
