package com.example.petshop.rest;

import com.example.petshop.config.MailerService;
import com.example.petshop.entity.*;
import com.example.petshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RequestMapping("/api/order")
@RestController
public class RestOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderStatusService orderStatusService;

    @Autowired
    private OrderPayMentService orderPayMentService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderProductDetailService orderProductDetailService;

    @Autowired
    private MailerService mailerService;

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
        return orderService.save(order);
    }

    @PutMapping("/{id}")
    public Order update(Order order, @PathVariable("id") Integer id) {
        order.setId(id);
        return orderService.save(order);
    }

    @PutMapping("/{order-id}/{order-status}")
    public Order updateStatus(@PathVariable("order-id") Integer id, @PathVariable("order-status") Integer orderStatus) {
        String fullname = orderService.getById(id).getUserName().getFullName();
        String email = orderService.getById(id).getUserName().getEmail();
        Order order = orderService.getById(id);
        if (orderStatus == 4) { // Order đã giao
            PaymentStatus paymentStatus = orderPayMentService.getById(2); // Đã thanh toán
            order.setPaymentStatusID(paymentStatus);
        }
        OrderStatus status = orderStatusService.getByStatus(orderStatus);
        order.setOrderStatusID(status);
        mailerService.sendOrderStatusEmail(email, "Đơn hàng của bạn đã được cập nhật",
                "Đơn hàng của bạn đã được cập nhật thành " + status.getStatusName() + "vào lúc " + new Date(),
                fullname, order.getId().toString());
        return orderService.save(order);
    }

    @DeleteMapping("/{id}")
    public void delete(Order order) {
        orderService.deleteById(order.getId());
    }

    @GetMapping("/history")
    public List<Order> getHistory(Principal principal) {
        if (principal == null) {
            return null;
        } else {
            User user = userService.findByUsername(principal.getName());
            return orderService.getHistory(user.getUsername());
        }
    }
}