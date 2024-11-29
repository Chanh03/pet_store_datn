package com.example.petshop.service;

import com.example.petshop.entity.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {
    List<Review> getReviewsByProductId(int id);

    List<Review> getAll();

    List<Review> getRatingsByProductId(int id);

    double getAverageRatingByProductId(int id);

    boolean isOrdered(String username, int id);

    Review save(Review review);

    List<Review> getReviewsMoiNhatVaTren4();
}
