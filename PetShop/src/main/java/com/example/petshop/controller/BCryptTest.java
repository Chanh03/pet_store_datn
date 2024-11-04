package com.example.petshop.controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptTest {
    public static void main(String[] args) {
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    	String rawPassword = "your_raw_password";
    	String encodedPassword = encoder.encode(rawPassword);

    	System.out.println("Raw password: " + rawPassword);
    	System.out.println("Encoded password: " + encodedPassword);

    	boolean match = encoder.matches(rawPassword, encodedPassword);
    	if(encodedPassword == encodedPassword) {
    		System.err.println("True");
    	}
    	System.out.println("Passwords match: " + match);
    }
}
