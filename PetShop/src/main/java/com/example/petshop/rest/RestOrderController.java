package com.example.petshop.rest;

import com.example.petshop.config.MailerService;
import com.example.petshop.entity.*;
import com.example.petshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.relational.core.sql.In;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    private ProductService productService;

    @Autowired
    private MailerService mailerService;

    @GetMapping
    public List<Order> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable("id") Integer id) {
        return orderService.getByOrderId(id);
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
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        String formattedDate = now.format(formatter);
        String fullname = orderService.getById(id).getUserName().getFullName();
        String email = orderService.getById(id).getUserName().getEmail();
        Order order = orderService.getById(id);
        if (orderStatus == 4) { // Order đã giao
            PaymentStatus paymentStatus = orderPayMentService.getById(2); // Đã thanh toán
            order.setPaymentStatusID(paymentStatus);
        }
        if (orderStatus == 5) { // Order bị hủy
            List<OrderProductDetail> orderProductDetails = orderProductDetailService.getByOrderID(order);
            for (OrderProductDetail detail : orderProductDetails) {
                Product product = detail.getProductID();
                product.setQuantity(detail.getQuantity() + product.getQuantity());
                productService.save(product);
            }
        }
        String emailOrderMessage = "";
        if (orderStatus == 2) {
            emailOrderMessage = "Đơn hàng của bạn đã được <b style='color: white; text-transform: uppercase; background-color: blue; padding: 5px 10px; border-radius: 5px;'>xác nhận</b> vào lúc <b>" + formattedDate + "</b>";
        } else if (orderStatus == 3) {
            emailOrderMessage = "Đơn hàng của bạn đã được giao cho <b style='color: black; text-transform: uppercase; background-color: yellow; padding: 5px 10px; border-radius: 5px;'>đơn vị vận chuyển</b> vào lúc <b>" + formattedDate + "vui lòng để ý điện thoại để nhận hàng</b>";
        } else if (orderStatus == 4) {
            emailOrderMessage = "Đơn hàng của bạn đã được <b style='color: white; text-transform: uppercase; background-color: green; padding: 5px 10px; border-radius: 5px;'>giao thành công</b> vào lúc <b>" + formattedDate + "</b>";
        } else if (orderStatus == 5) {
            emailOrderMessage = "Đơn hàng của bạn đã bị <b style='color: white; text-transform: uppercase; background-color: red; padding: 5px 10px; border-radius: 5px;'>hủy</b> vào lúc <b>" + formattedDate + "</b>";
        }
        OrderStatus status = orderStatusService.getByStatus(orderStatus);
        order.setOrderStatusID(status);
        mailerService.sendOrderStatusEmail(email, "Đơn hàng của bạn đã được cập nhật", emailOrderMessage,
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

    @PutMapping("/status/{id}")
    public Order updateStatus(@PathVariable("id") Integer id) {
        Order order = orderService.getByOrderId(id);
        List<OrderProductDetail> orderProductDetails = orderProductDetailService.getByOrderID(order);
        for (OrderProductDetail detail : orderProductDetails) {
            Product product = detail.getProductID();
            product.setQuantity(detail.getQuantity() + product.getQuantity());
            productService.save(product);
        }
        OrderStatus orderStatus = orderStatusService.getById(5);
        order.setOrderStatusID(orderStatus);
        return orderService.save(order);

    }

    @GetMapping("/filter")
    public List<Order> getByDate(@RequestParam(value = "from") @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
                                 @RequestParam(value = "to") @DateTimeFormat(pattern = "yyyy-MM-dd") Date to) {
        if (from == null || to == null) {
            return orderService.getAll();
        } else if (from.after(to)) {
            return orderService.findOrdersByDate(from, new Date());
        }
        return orderService.findOrdersByDate(from, to);
    }
}