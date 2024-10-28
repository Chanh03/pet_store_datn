package com.example.petshop.service;

import com.example.petshop.entity.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
	List<Product> getAll();

	Product getById(int id);

	Product save(Product product);

	void deleteById(int id);

	Page<Product> getPaginatedProduct(Pageable pageable);

	Page<Product> searchProduct(String keyword, Pageable pageable);
}