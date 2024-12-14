package com.example.petshop.controller;

import com.example.petshop.entity.*;
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

    @Autowired
    private PetCategoryService petCategoryService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderProductDetailService orderProductDetailService;

    @ModelAttribute("user")
    public User user(Authentication authentication, Principal principal) {
        if (principal == null) {
            return null;
        } else {
            return userService.findByUsername(principal.getName());
        }
    }

    @RequestMapping({"/", "/trang-chu", "/home"})
    public String home(Model model, Authentication authentication) {
        List<Product> nextSixProducts = productService.getAllByCreatedDateAndEnable();
        List<Pet> nextSixPet = petService.getAllByCreatedDate();
        List<PetCategory> petCategories = petCategoryService.getAll();
        List<Review> reviewsMoiNhatVaTren4 = reviewService.getReviewsMoiNhatVaTren4();

        model.addAttribute("nextSixProducts", nextSixProducts);
        model.addAttribute("nextSixPet", nextSixPet);
        model.addAttribute("productCategories", productCategoryService.getAll());
        model.addAttribute("slides", slideBarService.getAll());
        model.addAttribute("petCategories", petCategories);
        model.addAttribute("reviews", reviewsMoiNhatVaTren4);
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

    @RequestMapping("/successVnpay/{id}")
    public String successVnpay(@PathVariable(name = "id") int id) {
        String orderID = String.valueOf(id);
        if (orderID.length() < 8) {
            return "redirect:/access-denied";
        }
        return "security/successVnpay";
    }

    @RequestMapping("/failVnpay")
    public String failVnpay() {
        return "security/failVnpay";
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
    public String cartPayMent(Model model, Principal principal) {
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

    @RequestMapping("/history-detail/{id}")
    public String historyDetail(Model model, Principal principal, @PathVariable(name = "id") int id) {
        Order order = orderService.getByOrderId(id);
        List<OrderProductDetail> orderDetailHistory = orderProductDetailService.getByOrderID(order);
        if (order == null) {
            return "redirect:/access-denied";
        }
        System.out.println(orderDetailHistory);
        model.addAttribute("order", order);
        model.addAttribute("orderDetailHistory", orderDetailHistory);
        return "/layout/_historyOrderDetail";
    }

    @RequestMapping("/service")
    public String service() {
        return "layout/_service";
    }
}