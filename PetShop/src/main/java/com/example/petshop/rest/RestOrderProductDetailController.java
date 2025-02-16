package com.example.petshop.rest;

import com.example.petshop.entity.Order;
import com.example.petshop.entity.OrderProductDetail;
import com.example.petshop.entity.Product;
import com.example.petshop.service.OrderProductDetailService;
import com.example.petshop.service.OrderService;
import com.example.petshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin("*")
@RequestMapping("/api/order-detail")
@RestController
public class RestOrderProductDetailController {

    @Autowired
    private OrderProductDetailService orderProductDetailService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<OrderProductDetail> all() {
        return orderProductDetailService.getAll();
    }

    @GetMapping("/{id}")
    public List<OrderProductDetail> getAll(@PathVariable("id") Integer id) {
        Order order = orderService.getByOrderId(id);
        return orderProductDetailService.getByOrderID(order);
    }

    @PostMapping
    public OrderProductDetail createOrderProductDetail(@RequestBody OrderProductDetail orderProductDetail) {
        return orderProductDetailService.save(orderProductDetail);
    }

    @PutMapping("/{orderID}/{productID}")
    public OrderProductDetail updateOrderProductDetail(@PathVariable("orderID") Integer orderID, @PathVariable("productID") Integer productID) {
        Order order = orderService.getByOrderId(orderID);
        Product product = productService.getById(productID);
        OrderProductDetail orderProductDetail1 = orderProductDetailService.findByOrderIDAndProductID(order, product);
        orderProductDetail1.setReviewStatus(true);
        return orderProductDetailService.save(orderProductDetail1);
    }

    @GetMapping("/top5-product-day")
    public List<Object[]> getTop5ProductInDay() {
        LocalDate localDate = LocalDate.now();
        List<Order> order = orderService.findOrdersByDate(java.sql.Date.valueOf(localDate), java.sql.Date.valueOf(localDate));
        return orderProductDetailService.getTop5Product(order);
    }

    @GetMapping("/top5-product-week")
    public List<Object[]> getTop5ProductInWeek() {
        LocalDate localDate = LocalDate.now();
        LocalDate sevenDaysAgo = localDate.minusDays(6);
        List<Order> order = orderService.findOrdersByDate(java.sql.Date.valueOf(sevenDaysAgo), java.sql.Date.valueOf(localDate));
        return orderProductDetailService.getTop5Product(order);
    }

    @GetMapping("/top5-product-month")
    public List<Object[]> getTop5ProductInMonth() {
        LocalDate localDate = LocalDate.now();
        int month1 = localDate.getMonthValue();
        int year1 = localDate.getYear();
        List<Order> order = orderService.findOrdersByMonth(month1, year1);
        return orderProductDetailService.getTop5Product(order);
    }

    @GetMapping("/top5-product-year")
    public List<Object[]> getTop5ProductInYear() {
        LocalDate localDate = LocalDate.now();
        int year1 = localDate.getYear();
        List<Order> order = orderService.findOrdersByYear(year1);
        return orderProductDetailService.getTop5Product(order);
    }
}
