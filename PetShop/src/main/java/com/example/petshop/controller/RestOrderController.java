package com.example.petshop.controller;

import com.example.petshop.entity.Order;
import com.example.petshop.entity.User;
import com.example.petshop.service.OrderPaymentStatusService;
import com.example.petshop.service.OrderService;
import com.example.petshop.service.OrderStatusService;
import com.example.petshop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RequestMapping("/api/order")
@RestController
public class RestOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderStatusService orderStatusService;

    @Autowired
    private OrderPaymentStatusService orderPaymentStatusService;

    @GetMapping
    public List<Order> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable("id") Integer id) {
        return orderService.getById(id);
    }

    @PostMapping
    public Order save(@RequestBody Order order) {
        order.setOrderDate(new Date());
        return orderService.save(order);
    }

    @PutMapping("/{id}")
    public Order update(Order order, @PathVariable("id") Integer id) {
        order.setId(id);
        return orderService.save(order);
    }

    @DeleteMapping("/{id}")
    public void delete(Order order) {
        orderService.deleteById(order.getId());
    }
}