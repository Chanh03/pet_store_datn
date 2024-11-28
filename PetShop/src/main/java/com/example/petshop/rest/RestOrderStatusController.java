package com.example.petshop.rest;

import com.example.petshop.entity.OrderStatus;
import com.example.petshop.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/api/orderStatus")
@RestController
public class RestOrderStatusController {
    @Autowired
    private OrderStatusService orderStatusService;

    @GetMapping
    public List<OrderStatus> getAllOrderStatus() {
        return orderStatusService.findAll();
    }

    @GetMapping("/{id}")
    public OrderStatus getOrderStatus(@PathVariable int id) {
        return orderStatusService.findById(id);
    }
}
