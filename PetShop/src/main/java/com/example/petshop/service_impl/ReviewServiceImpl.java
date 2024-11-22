package com.example.petshop.service_impl;

import com.example.petshop.entity.Product;
import com.example.petshop.entity.Review;
import com.example.petshop.repo.ProductRepo;
import com.example.petshop.repo.ReviewRepo;
import com.example.petshop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepo reviewRepo;

    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<Review> getReviewsByProductId(int id) {
        Product product = productRepo.findById(id).orElse(null);
        if (product == null) {
            return null;
        }
        return reviewRepo.findByProductID(product);
    }

    @Override
    public List<Review> getAll() {
        return reviewRepo.findAll();
    }

    @Override
    public List<Review> getRatingsByProductId(int id) {
        return reviewRepo.findByProductIDAndRatingGreaterThan(productRepo.findById(id).orElse(null), 0);
    }

    @Override
    public double getAverageRatingByProductId(int id) {
        List<Review> reviews = getRatingsByProductId(id);
        if (reviews == null) {
            return 0;
        }
        double sum = 0;
        for (Review review : reviews) {
            sum += review.getRating();
        }
        double tb = sum / reviews.size();
        return Math.round(tb * 10) / 10.0;
    }

    @Override
    public boolean isOrdered(String username, int id) {
        return false;
    }

    @Override
    public Review save(Review review) {
        return reviewRepo.save(review);
    }

    @Override
    public List<Review> getReviewsMoiNhatVaTren4() {
        return reviewRepo.findTop4ByOrderByCreatedDateDesc();
    }
}
