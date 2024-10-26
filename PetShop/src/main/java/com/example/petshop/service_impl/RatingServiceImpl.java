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

    public List<Rating> getRatingsByProductId(int productId) {
        Product product = productService.getById(productId);
        return ratingRepo.findByProductID(product);
    }
}
