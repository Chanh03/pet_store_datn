package com.example.petshop.repo;

import com.example.petshop.entity.Product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
	Page<Product> findByProductDescriptionContainingIgnoreCase(String keyword, Pageable pageable);

	List<Product> findByProductCategoryID_IdAndIdNot(Integer categoryId, Integer productId);
}