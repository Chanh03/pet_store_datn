package com.example.petshop.service_impl;

import com.example.petshop.entity.Review;
import com.example.petshop.entity.Product;
import com.example.petshop.repo.ReviewRepo;
import com.example.petshop.repo.ProductRepo; // Thêm ProductRepo để lấy đối tượng Product
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
}
