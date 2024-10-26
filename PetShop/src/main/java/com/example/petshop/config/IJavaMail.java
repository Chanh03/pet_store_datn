package com.example.petshop.config;

import java.util.UUID;

public interface IJavaMail {
    boolean sendEmail(String to,String subject,String message,String name, String uuid);
    boolean confirmChangePassword(String to,String subject,String message,String name, String uuid);
}