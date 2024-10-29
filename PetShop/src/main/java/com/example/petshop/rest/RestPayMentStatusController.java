package com.example.petshop.rest;

import com.example.petshop.entity.OrderStatus;
import com.example.petshop.entity.PaymentStatus;
import com.example.petshop.service.OrderPayMentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping("/api/paymentStatus")
@RestController
public class RestPayMentStatusController {
    @Autowired
    private OrderPayMentService orderPayMentService;

    @GetMapping("/{id}")
    public PaymentStatus getOrderStatus(@PathVariable int id) {
        return orderPayMentService.findById(id);
    }
}
