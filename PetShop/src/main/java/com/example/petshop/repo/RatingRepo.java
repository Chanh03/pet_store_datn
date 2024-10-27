package com.example.petshop.repo;

import com.example.petshop.entity.Product;
import com.example.petshop.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepo extends JpaRepository<Rating, Integer> {
	List<Rating> findByProductID(Product product);
}