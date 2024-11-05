package com.example.petshop.rest;

import com.example.petshop.entity.Order;
import com.example.petshop.entity.OrderProductDetail;
import com.example.petshop.service.OrderProductDetailService;
import com.example.petshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/api/order-detail")
@RestController
public class RestOrderProductDetailController {

    @Autowired
    private OrderProductDetailService orderProductDetailService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public List<OrderProductDetail> getAll(@PathVariable("id") Integer id) {
        Order order = orderService.getById(id);
        return orderProductDetailService.getByOrderID(order);
    }
}
