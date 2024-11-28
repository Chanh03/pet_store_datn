package com.example.petshop.service;

import com.example.petshop.entity.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductCategoryService {
    List<ProductCategory> getAll();
    ProductCategory getById(int id);
    ProductCategory save(ProductCategory productCategory);
    void deleteById(int id);
}
