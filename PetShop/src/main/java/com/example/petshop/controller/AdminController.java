package com.example.petshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quan-tri-he-thong")
public class AdminController {
    @RequestMapping()
    public String admin(Model model) {
        return "admin/_dashboard";
    }

    @RequestMapping("/pet")
    public String adminPet(Model model) {
        return "admin/_pets";
    }

    @RequestMapping("/product")
    public String adminProduct(Model model) {
        return "admin/_products";
    }

    @RequestMapping("/user")
    public String adminUser(Model model) {
        return "admin/_users";
    }
}
