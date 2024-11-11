package com.example.petshop.service_impl;

import com.example.petshop.entity.Product;
import com.example.petshop.entity.Rating;
import com.example.petshop.repo.RatingRepo;
import com.example.petshop.service.ProductService;
import com.example.petshop.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepo ratingRepo;

    @Autowired
    private ProductService productService;

    @Override
    public List<Rating> getAll() {
        return ratingRepo.findAll();
    }

    @Override
    public Rating save(Rating rating) {
        return ratingRepo.save(rating);
    }

    public List<Rating> getRatingsByProductId(int productId) {
        Product product = productService.getById(productId);
        return ratingRepo.findByProductID(product);
    }

    // Phương thức làm tròn điểm đánh giá thành số lẻ
    public double roundRating(double average) {
        return Math.round(average * 2) / 2.0; // Làm tròn đến số lẻ (0.5, 1.0, 1.5,...)
    }

    // Tính điểm đánh giá trung bình cho sản phẩm
    public double getAverageRatingByProductId(int productId) {
        List<Rating> ratings = getRatingsByProductId(productId);
        if (ratings.isEmpty()) {
            return 0; // Không có đánh giá nào
        }
        double total = 0;
        for (Rating rating : ratings) {
            total += rating.getNumber();
        }
        double average = total / ratings.size(); // Tính điểm trung bình
        return roundRating(average); // Làm tròn điểm thành số lẻ
    }
}
