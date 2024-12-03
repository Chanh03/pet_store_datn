package com.example.petshop.rest;

import com.example.petshop.entity.Subscription;
import com.example.petshop.repo.SubscriptionRepo;
import com.example.petshop.service.SubscriptionService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subscriptions")
@CrossOrigin("*")
public class RestSubscriptionController {

    @Autowired
    private SubscriptionRepo subscriptionRepository;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private SpringTemplateEngine templateEngine;

    @PostMapping("/subscribe")
    public Subscription subscribe(@RequestBody Subscription subscription) throws MessagingException {
        Optional<Subscription> existingSubscription = subscriptionRepository.findByEmail(subscription.getEmail());
        if (existingSubscription.isPresent()) {
            throw new RuntimeException("Email đã được đăng ký!");
        }

        // Lưu đăng ký mới
        Subscription savedSubscription = subscriptionRepository.save(subscription);

        // Tạo nội dung email bằng Thymeleaf
        Context context = new Context();
        context.setVariable("name", subscription.getName());
        context.setVariable("email", subscription.getEmail());
        context.setVariable("phone_number", subscription.getPhoneNumber());

        String body = templateEngine.process("/others/_mailForm", context);

        // Tạo và gửi email
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(subscription.getEmail());
        helper.setSubject("ĐĂNG KÝ NHẬN TIN CỬA HÀNG THÚ CƯNG NINJAS");
        helper.setText(body, true);

        mailSender.send(mimeMessage);

        return savedSubscription;
    }

    @GetMapping
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    @PostMapping("/send")
    public void sendEmailToSubscribers(@RequestParam("subject") String subject,
                                       @RequestParam("content") String content) throws MessagingException {
        List<Subscription> subscriptions = subscriptionRepository.findAll();

        for (Subscription subscription : subscriptions) {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(subscription.getEmail());
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);
        }
    }
}