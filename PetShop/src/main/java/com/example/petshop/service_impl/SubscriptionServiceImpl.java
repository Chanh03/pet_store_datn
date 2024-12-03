package com.example.petshop.service_impl;

import com.example.petshop.entity.Subscription;
import com.example.petshop.repo.SubscriptionRepo;
import com.example.petshop.service.SubscriptionService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepo subscriptionRepo;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public Subscription createSubscription(Subscription subscription) {
        Optional<Subscription> existing = subscriptionRepo.findByEmail(subscription.getEmail());
        if (existing.isPresent()) {
            throw new RuntimeException("Email already registered!");
        }
        return subscriptionRepo.save(subscription);
    }

    @Override
    public Subscription getSubscriptionById(Long id) {
        return subscriptionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Subscription not found with id: " + id));
    }

    @Override
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepo.findAll();
    }

    @Override
    public void deleteSubscriptionById(Long id) {
        if (!subscriptionRepo.existsById(id)) {
            throw new RuntimeException("Subscription not found with id: " + id);
        }
        subscriptionRepo.deleteById(id);
    }

    @Override
    public void sendEmailToSubscribers(String subject, String content) {
        List<Subscription> subscriptions = subscriptionRepo.findAll();

        for (Subscription subscription : subscriptions) {
            try {
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                helper.setTo(subscription.getEmail());
                helper.setSubject(subject);
                helper.setText(content, true);

                mailSender.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
                throw new RuntimeException("Error while sending email to: " + subscription.getEmail());
            }
        }
    }
}