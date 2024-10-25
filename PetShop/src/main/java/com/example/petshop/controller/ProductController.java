package com.example.petshop.controller;

import com.example.petshop.entity.Product;
import com.example.petshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/product/detail/{id}")
    public String product(Model model, @PathVariable int id){
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        return "/layout/_productDetail";
    }
}
