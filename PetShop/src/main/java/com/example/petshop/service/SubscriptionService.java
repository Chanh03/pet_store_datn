package com.example.petshop.service;

import com.example.petshop.entity.Subscription;

import java.util.List;

public interface SubscriptionService {
    Subscription createSubscription(Subscription subscription);

    Subscription getSubscriptionById(Long id);

    List<Subscription> getAllSubscriptions();

    void deleteSubscriptionById(Long id);

    void sendEmailToSubscribers(String subject, String content);
}