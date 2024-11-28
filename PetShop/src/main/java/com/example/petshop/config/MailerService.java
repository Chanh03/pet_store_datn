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
import org.springframework.stereotype.Component;

@Component
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
                    + "<p>Chào " + name + ",</p>"
                    + "<p>Nhấn vào đường dẫn dưới đây để xác nhận email của bạn:</p>"
                    + "<a href='http://localhost:8080/confirmation?confirmation_token=" + uuid + "'>Confirm your email</a>"
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
    public void sendOrderStatusEmail(String to, String subject, String message, String name, String orderID) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", EmailProperty.HOST_NAME);
        props.put("mail.smtp.port", EmailProperty.TLS_PORT);
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EmailProperty.APP_EMAIL, EmailProperty.APP_PASSWORD);
            }
        });
        try {
            MimeMessage orderMessage = new MimeMessage(session);
            orderMessage.setFrom(new InternetAddress(EmailProperty.APP_EMAIL));
            orderMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            orderMessage.setSubject(subject);
            String htmlMessage = "<html>\n" +
                    "    <body style=\"font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;\">\n" +
                    "        <div style=\"border:1px solid black;max-width: 600px; margin: 0 auto; background-color: #ffffff; border-radius: 8px; box-shadow: 0 4px 8px rgba(0,0,0,0.1); overflow: hidden;\">\n" +
                    "            <div style=\"padding: 20px; background: linear-gradient(to right, #0091fe 0%, #cc0033 100%); color: #ffffff; text-align: center;\">\n" +
                    "                <h1 style=\"margin: 0; font-size: 24px;\">Đơn hàng số #{id} đã được cập nhật</h1>\n" +
                    "            </div>\n" +
                    "            <div style=\"padding: 20px;\">\n" +
                    "                <h3 style=\"color: #333333;\">Chào <strong>{name}</strong>,</h3>\n" +
                    "                <h4 style=\"color: #333333; line-height: 1.6;\">{message}</h4>\n" +
                    "                <h4 style=\"text-align: center;\">\n" +
                    "                    <a href='http://localhost:8080/history-detail/{id}' style=\"display: inline-block; padding: 10px 20px; color: #ffffff; background-color: #0099ff; border-radius: 5px; text-decoration: none;\">Xem chi tiết đơn hàng</a>\n" +
                    "                </h4>\n" +
                    "            </div>\n" +
                    "            <div style=\"padding: 15px; background-color: #f0f0f0; color: #999999; text-align: center; font-size: 12px;\">\n" +
                    "                <p style=\"margin: 0;\">© 2024 Ninjas Pet. Bảo mật thông tin của bạn là ưu tiên hàng đầu của chúng tôi.</p>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </body>\n" +
                    "</html>\n";
            htmlMessage = htmlMessage.replace("{id}", orderID);
            htmlMessage = htmlMessage.replace("{name}", name);
            htmlMessage = htmlMessage.replace("{message}", message);
            orderMessage.setContent(htmlMessage, "text/html; charset=UTF-8");
            Transport.send(orderMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean confirmChangePassword(String to, String subject, String messageContent, String name, String uuid) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", EmailProperty.HOST_NAME);
        props.put("mail.smtp.socketFactory.port", EmailProperty.TLS_PORT);
        props.setProperty("mail.smtp.port", "8080");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", EmailProperty.TLS_PORT);
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EmailProperty.APP_EMAIL, EmailProperty.APP_PASSWORD);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EmailProperty.APP_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            String htmlMessage = "<html><body style='font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;'>"
                    + "<div style='max-width: 600px; margin: 0 auto; background-color: #ffffff; border-radius: 8px; box-shadow: 0 4px 8px rgba(0,0,0,0.1); overflow: hidden;'>"
                    + "  <div style='padding: 20px; background-color: #0066cc; color: #ffffff; text-align: center;'>"
                    + "    <h1 style='margin: 0; font-size: 24px;'>Xác Nhận Đổi Mật Khẩu</h1>"
                    + "  </div>"
                    + "  <div style='padding: 20px;'>"
                    + "    <p style='color: #333333;'>Chào " + name + ",</p>"
                    + "    <p style='color: #333333; line-height: 1.6;'>Bạn đã yêu cầu đổi mật khẩu. Để xác nhận, vui lòng nhấp vào đường link dưới đây:</p>"
                    + "    <p style='text-align: center;'>"
                    + "      <a href='http://localhost:8080/new-password/" + name + "?token=" + uuid
                    + "' style='display: inline-block; padding: 10px 20px; color: #ffffff; background-color: #0099ff; border-radius: 5px; text-decoration: none;'>Đổi mật khẩu mới</a>"
                    + "    </p>"
                    + "    <p style='color: #666666; font-size: 14px; line-height: 1.5;'>Nếu bạn không phải là người yêu cầu, hãy bỏ qua email này và bảo vệ thông tin tài khoản cá nhân.</p>"
                    + "  </div>"
                    + "  <div style='padding: 15px; background-color: #f0f0f0; color: #999999; text-align: center; font-size: 12px;'>"
                    + "    <p style='margin: 0;'>© 2024 Công Ty ABC. Bảo mật thông tin của bạn là ưu tiên hàng đầu của chúng tôi.</p>"
                    + "  </div>"
                    + "</div>"
                    + "</body></html>";
            message.setContent(htmlMessage, "text/html; charset=UTF-8");
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