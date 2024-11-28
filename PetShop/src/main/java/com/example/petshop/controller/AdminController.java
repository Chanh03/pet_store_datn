package com.example.petshop.controller;

import com.example.petshop.entity.Subscription;
import com.example.petshop.repo.SubscriptionRepo;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.TemplateEngine;

import java.util.List;

@Controller
@RequestMapping("/quan-tri-he-thong")
public class AdminController {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SubscriptionRepo subscriptionRepository;

    @Autowired
    private TemplateEngine templateEngine;

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
        // Hiển thị danh sách email đã đăng ký
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        model.addAttribute("subscriptions", subscriptions);
        return "admin/_send_notification";
    }

    @PostMapping("/send")
    public String sendEmailToSubscribers(@RequestParam("subject") String subject,
                                         @RequestParam("content") String content,
                                         Model model) {
        List<Subscription> subscriptions = subscriptionRepository.findAll();

        for (Subscription subscription : subscriptions) {
            // Tạo email và gửi đi
            MimeMessage message = mailSender.createMimeMessage();
            try {
                MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                helper.setTo(subscription.getEmail());
                helper.setSubject(subject);
                helper.setText(content, true);

                mailSender.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

        return "admin/_send_notification";
    }
}
