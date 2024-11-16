package com.example.petshop.repo;

import com.example.petshop.entity.Product;
import com.example.petshop.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Integer> {
    List<Review> findByProductID(Product product);

    List<Review> findByProductIDAndRatingGreaterThan(Product product, int i);
}
