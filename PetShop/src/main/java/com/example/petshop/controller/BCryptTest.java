package com.example.petshop.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptTest {
    public static void main(String[] args) {
    	try {
            URL url = new URL("https://api.ipify.org?format=text");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String publicIp = in.readLine();
            in.close();

            System.out.println("Public IP: " + publicIp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
