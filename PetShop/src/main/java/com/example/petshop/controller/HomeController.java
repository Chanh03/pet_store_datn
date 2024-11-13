package com.example.petshop.controller;

import com.example.petshop.entity.Pet;
import com.example.petshop.entity.Product;
import com.example.petshop.entity.User;
import com.example.petshop.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.Console;
import java.security.Principal;
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

    @ModelAttribute("fullname")
    public void getUser(Model model, HttpServletRequest request) {
        try {
            User user = userService.findByUsername(request.getUserPrincipal().getName());
            if (user != null) {

                model.addAttribute("user", user);
            }
        } catch (Exception e) {
            model.addAttribute("user", null);
        }
    }

    @RequestMapping({"/", "/trang-chu", "/home"})
    public String home(Model model, Authentication authentication) {

        List<Product> productsList = productService.getAll();
        Product latestProduct = productsList.stream().max(Comparator.comparingInt(Product::getId))
                .orElseThrow(() -> new NoSuchElementException("No product found"));
        Collection<Product> nextSixProducts = productsList.stream().skip(1).limit(6).collect(Collectors.toList());
        List<Pet> petList = petService.getAll();
        Pet latestPet = petList.stream().max(Comparator.comparing(Pet::getPetID))
                .orElseThrow(() -> new NoSuchElementException("No pet found"));
        Collection<Pet> nextSixPet = petList.stream().skip(1).limit(6).toList();

        model.addAttribute("nextSixProducts", nextSixProducts);
        model.addAttribute("firstPet", latestPet);
        model.addAttribute("nextSixPet", nextSixPet);
        model.addAttribute("productCategories", productCategoryService.getAll());
        model.addAttribute("slides", slideBarService.getAll());

        return "/layout/_main";
    }

    @RequestMapping("/login")
    public String login(@AuthenticationPrincipal OidcUser principal, Model model, @RequestParam(value = "error", required = false) boolean error,
                        @RequestParam(value = "success", required = false) boolean success) {
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

    @RequestMapping("/register")
    public String register() {
        return "security/register";
    }

    @RequestMapping("/confirmation")
    public String confirmation(@RequestParam("confirmation_token") String confirmation_token) {
        return "security/confirmation";
    }

    @RequestMapping("/sendMail")
    public String sendMail() {
        return "security/sendMail";
    }

    @RequestMapping("/forgot-password")
    public String forgotPassword() {
        return "security/forgot-password";
    }

    @RequestMapping("/information")
    public String information(Authentication authentication, Model model) {
        //Hàm dưới đây kiểm tra username của người dùng trong trang information cho đăng nhập bằng gg và cách thường
        String username = null;
        if (authentication != null && authentication.isAuthenticated()) {
            if (authentication.getPrincipal() instanceof OAuth2User) {
                OAuth2User user = (OAuth2User) authentication.getPrincipal();
                if (user.getAttributes().containsKey("sub")) {
                    username = user.getAttributes().get("sub").toString();
                } else {
                    username = user.getName();
                }
            } else {
                username = authentication.getName();
            }
        }
        model.addAttribute("usernameInfomation", username);
        return "security/information";
    }

    @RequestMapping("/new-password/{username}")
    public String newPassword(@PathVariable String username, @RequestParam("token") String token) {
        return "security/new-password";
    }

    @RequestMapping("/access-denied")
    public String accessDenied(Model model) {
        return "security/access-denied";
    }

    @RequestMapping("/cart-detail")
    public String cartDetail(Model model) {
        return "/layout/_cartDetail";
    }

    @RequestMapping("/cart-payMent")
    public String cartPayMent(Model model) {
        return "layout/_payMent";
    }

    @RequestMapping("/notifications")
    public String notification(Model model) {
        return "notificationDemo";
    }

    @RequestMapping("/history")
    public String historyCart(Model model, Principal principal) {
        try {
            User userHistory = userService.findByUsername(principal.getName());
            model.addAttribute("userHistory", userHistory);
            return "/layout/_historyCart";
        } catch (Exception e) {
            return "redirect:/access-denied";
        }
    }

    @RequestMapping("/history-detail")
    public String historyCartDetail(Model model, Principal principal) {
        User userHistoryDetail = userService.findByUsername(principal.getName());
        model.addAttribute("userHistoryDetail", userHistoryDetail);
        return "layout/_historyCartDetail";
    }
}