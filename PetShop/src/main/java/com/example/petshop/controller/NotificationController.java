package com.example.petshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/send-notification")
    public void sendNotification(@RequestParam String message) {
        // Gửi thông báo tới tất cả client đăng ký trên "/trang-chu"
        simpMessagingTemplate.convertAndSend("/", message);
    }
}
