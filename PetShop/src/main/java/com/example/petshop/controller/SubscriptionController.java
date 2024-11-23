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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;
import java.util.Optional;

@Controller
public class SubscriptionController {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private SubscriptionRepo subscriptionRepository;

	@Autowired
	private TemplateEngine templateEngine;

	@GetMapping("/subscribe")
	public String showSubscriptionForm(Model model) {
		model.addAttribute("subscription", new Subscription());
		return "/layout/_main";
	}

	@PostMapping("/subscribe")
	public String subscribe(@ModelAttribute Subscription subscription, Model model) throws MessagingException {
		Optional<Subscription> existingSubscription = subscriptionRepository.findByEmail(subscription.getEmail());
		if (existingSubscription.isPresent()) {
			model.addAttribute("message", false);
			return "/layout/_main";
		}

		subscriptionRepository.save(subscription);

		Context context = new Context();
		context.setVariable("name", subscription.getName());
		context.setVariable("email", subscription.getEmail());
		context.setVariable("phone_number", subscription.getPhoneNumber());

		String body = templateEngine.process("/others/mailForm", context);

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setTo(subscription.getEmail());
		helper.setSubject("ĐĂNG KÝ NHẬN TIN CỬA HÀNG THÚ CƯNG NINJAS");
		helper.setText(body, true);

		mailSender.send(mimeMessage);

		model.addAttribute("message", true);
		return "/layout/_main";
	}
	
	@GetMapping("/sendMail")
    public String showAdminPage(Model model) {
        // Hiển thị danh sách email đã đăng ký
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        model.addAttribute("subscriptions", subscriptions);
        return "/admin/_sendMail";
    }

    @PostMapping("/admin/send")
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

        model.addAttribute("message", "Tin nhắn đã được gửi tới tất cả người đăng ký!");
        return "result";
    }
}
