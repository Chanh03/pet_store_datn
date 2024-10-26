package com.example.petshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.petshop.entity.Review;

@Service
public interface ReviewService {

	List<Review> getReviewsByProductId(int id);
}
