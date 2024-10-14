package com.example.petshop.controller;

import com.example.petshop.entity.Pet;
import com.example.petshop.entity.Product;
import com.example.petshop.entity.User;
import com.example.petshop.service.PetService;
import com.example.petshop.service.ProductService;
import com.example.petshop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PetService petService;

    @ModelAttribute("fullname")
    public void getUser(Model model, HttpServletRequest request) {
        try {
            User user = userService.findByUsername(request.getUserPrincipal().getName());
            if (user != null) {
                model.addAttribute("fullname", user.getFullName());
            }
        } catch (Exception e) {
            model.addAttribute("fullname", "Khách");
        }
    }

    @RequestMapping({"/", "/trang-chu", "/home"})
    public String home(Model model) {
        List<Product> productsList = productService.getAll();
        Product latestProduct = productsList.stream().max(Comparator.comparingInt(Product::getId)).orElseThrow(() -> new NoSuchElementException("No product found"));
        Collection<Product> nextSixProducts = productsList.stream().skip(1).limit(6).collect(Collectors.toList());
        List<Pet> petList = petService.getAll();
        Pet latestPet = petList.stream().max(Comparator.comparing(Pet::getPetID)).orElseThrow(() -> new NoSuchElementException("No pet found"));
        Collection<Pet> nextSixPet = petList.stream().skip(1).limit(6).toList();
        model.addAttribute("firstProduct", latestProduct);
        model.addAttribute("nextSixProducts", nextSixProducts);
        model.addAttribute("firstPet", latestPet);
        model.addAttribute("nextSixPet", nextSixPet);
        return "/layout/_main";
    }

    @RequestMapping("/cart-detail")
    public String cart(Model model) {
        return "/layout/_cartDetail";
    }

    @RequestMapping("/pet")
    public String pet(Model model) {
        return "/layout/_petDetail";
    }

    @RequestMapping("/product")
    public String product(Model model) {
        return "/layout/_productDetail";
    }

    @RequestMapping("/login")
    public String login(Model model, @RequestParam(value = "error", required = false) boolean error, @RequestParam(value = "success", required = false) boolean success) {
        if (error) {
            model.addAttribute("message", "Đăng nhập thất bại!");
            model.addAttribute("loginStatus", false);
        }
        if (success) {
            model.addAttribute("message", "Đăng nhập thành công!");
            model.addAttribute("loginStatus", true);
        }
        return "security/login";
    }

    @RequestMapping("/access-denied")
    public String accessDenied(Model model) {
        return "security/access-denied";
    }


}
