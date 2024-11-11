package com.example.petshop.service;

import com.example.petshop.entity.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {
    List<Review> getReviewsByProductId(int id);
}
