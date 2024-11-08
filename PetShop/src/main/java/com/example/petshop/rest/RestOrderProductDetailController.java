package com.example.petshop.rest;

import com.example.petshop.entity.OrderProductDetail;
import com.example.petshop.service.OrderProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RequestMapping("/api/orderProductDetail")
@RestController
public class RestOrderProductDetailController {
    @Autowired
    private OrderProductDetailService orderProductDetailService;

    @GetMapping
    public List<OrderProductDetail> getAll(){
        return orderProductDetailService.findAll();
    }

    @GetMapping("/{id}")
    public void getByID(@PathVariable int id){
        orderProductDetailService.findById(id);
    }

    @PostMapping
    public OrderProductDetail create(@RequestBody OrderProductDetail orderProductDetail){
        return orderProductDetailService.save(orderProductDetail);
    }


}
