package com.example.petshop.config;

public interface IJavaMail {
    boolean sendEmail(String to, String subject, String message, String name, String uuid);

    void sendOrderStatusEmail(String to, String subject, String message, String name, String orderID);

    boolean confirmChangePassword(String to, String subject, String message, String name, String uuid);
}