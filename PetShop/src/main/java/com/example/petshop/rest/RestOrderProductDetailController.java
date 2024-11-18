package com.example.petshop.rest;

import com.example.petshop.entity.Order;
import com.example.petshop.entity.OrderProductDetail;
import com.example.petshop.entity.Product;
import com.example.petshop.service.OrderProductDetailService;
import com.example.petshop.service.OrderService;
import com.example.petshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<OrderProductDetail> all(){
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
    public OrderProductDetail updateOrderProductDetail(@PathVariable("orderID") Integer orderID,@PathVariable("productID") Integer productID) {
        Order order = orderService.getByOrderId(orderID);
        Product product = productService.getById(productID);
        OrderProductDetail orderProductDetail1 = orderProductDetailService.findByOrderIDAndProductID(order, product);
        orderProductDetail1.setReviewStatus(true);
        return orderProductDetailService.save(orderProductDetail1);
    }
}
