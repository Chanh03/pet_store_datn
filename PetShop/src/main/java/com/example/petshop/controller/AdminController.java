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

    @RequestMapping("/pet-category")
    public String petCategoryAdmin(Model model) {
        return "admin/_pet_category";
    }

    @RequestMapping("/pet")
    public String adminPet(Model model) {
        return "admin/_pets";
    }

    @RequestMapping("/product-category")
    public String productCategoryAdmin(Model model) {
        return "admin/_product_category";
    }

    @RequestMapping("/product")
    public String adminProduct(Model model) {
        return "admin/_products";
    }


    @RequestMapping("/user")
    public String adminUser(Model model) {
        return "admin/_users";
    }

    @RequestMapping("/order")
    public String adminOrder(Model model) {
        return "admin/_orders";
    }

    @RequestMapping("/authorization")
    public String adminAuthorization(Model model) {
        return "admin/_authority";
    }

    @RequestMapping("/slide-bar")
    public String adminSlideBar(Model model) {
        return "admin/_sildeBar";
    }

    @RequestMapping("/send-notification")
    public String adminSendNotification(Model model) {
        return "admin/_send_notification";
    }

    @RequestMapping("/voucher")
    public String adminVoucher(Model model) {
        return "admin/_voucher";
    }
}
