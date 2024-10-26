package com.example.petshop.config;



import java.util.Properties;
import java.util.UUID;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class MailerService implements IJavaMail {

    @Override
    public boolean sendEmail(String to, String subject, String messageContent, String name, String uuid) {
        // Thiết lập thuộc tính SMTP
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", EmailProperty.HOST_NAME);
        props.put("mail.smtp.socketFactory.port", EmailProperty.TLS_PORT);
        props.setProperty("mail.smtp.port", "8080");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", EmailProperty.TLS_PORT);

        // Lấy phiên làm việc với Authenticator
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EmailProperty.APP_EMAIL, EmailProperty.APP_PASSWORD);
            }
        });

        try {
            // Tạo tin nhắn email
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EmailProperty.APP_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            // Đoạn HTML để gửi email
            String htmlMessage = "<html><body>"
                    + "<p>Chào "+ name +",</p>"
                    + "<p>Nhấn vào đường dẫn dưới đây để xác nhận email của bạn:</p>"
                    + "<a href='http://localhost:8080/confirmation?confirmation_token="+uuid +"'>Confirm your email</a>"
                    + "</body></html>";

            // Gửi email với nội dung HTML
            message.setContent(htmlMessage, "text/html; charset=UTF-8");

            // Gửi email
            Transport.send(message);
            System.out.println("Email đã được gửi thành công tới: " + to);
            return true;
        } catch (MessagingException e) {
            System.err.println("Gửi email thất bại: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean confirmChangePassword(String to, String subject, String messageContent, String name, String uuid) {
        // Thiết lập thuộc tính SMTP
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", EmailProperty.HOST_NAME);
        props.put("mail.smtp.socketFactory.port", EmailProperty.TLS_PORT);
        props.setProperty("mail.smtp.port", "8080");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", EmailProperty.TLS_PORT);

        // Lấy phiên làm việc với Authenticator
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EmailProperty.APP_EMAIL, EmailProperty.APP_PASSWORD);
            }
        });

        try {
            // Tạo tin nhắn email
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EmailProperty.APP_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            // Đoạn HTML để gửi email
            String htmlMessage = "<html><body>"
                    + "<p>Chào "+ name +",</p>"
                    + "<p>Đây là đường link xác nhận đổi mật khẩu:</p>"
                    + "<a href='http://localhost:8080/new-password/"+ name + "?token="+uuid +"'>Nhấn để đổi mật khẩu mới</a>"
                    +"<p>Nếu bạn không phải là người yêu cầu cho việc làm này thì hãy bảo vệ thông tin tài khoản cá nhân, không để lộ cho người khác!</p>"
                    + "</body></html>";
            // Gửi email với nội dung HTML
            message.setContent(htmlMessage, "text/html; charset=UTF-8");

            // Gửi email
            Transport.send(message);
            System.out.println("Email đã được gửi thành công tới: " + to);
            return true;
        } catch (MessagingException e) {
            System.err.println("Gửi email thất bại: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

}