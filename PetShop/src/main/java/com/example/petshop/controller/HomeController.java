package com.example.petshop.controller;

import com.example.petshop.entity.Pet;
import com.example.petshop.entity.Product;
import com.example.petshop.entity.User;
import com.example.petshop.service.*;
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

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private SlideBarService slideBarService;

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
        model.addAttribute("productCategories", productCategoryService.getAll());
        model.addAttribute("slides", slideBarService.getAll());
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
            model.addAttribute("loginStatus", false);
        }
        if (success) {
            model.addAttribute("loginStatus", true);
        }
        return "security/login";
    }

    @RequestMapping("/access-denied")
    public String accessDenied(Model model) {
        return "security/access-denied";
    }

    @RequestMapping("/upload")
    public String upload(Model model) {
        return "upload";
    }
}
