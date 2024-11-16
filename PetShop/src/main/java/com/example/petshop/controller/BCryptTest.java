package com.example.petshop.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptTest {
	public static void main(String[] args) {
		String password = "hoanghien";
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = bCryptPasswordEncoder.encode(password);
		System.out.println(encodedPassword);
	}
}
