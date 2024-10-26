package com.example.petshop.service;

import com.example.petshop.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<Product> getAll();

    Product getById(int id);

    Product save(Product product);

    void deleteById(int id);
}
