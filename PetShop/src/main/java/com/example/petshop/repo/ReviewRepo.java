package com.example.petshop.repo;

import com.example.petshop.entity.Review;
import com.example.petshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Integer> {
    List<Review> findByProductID(Product product);
}