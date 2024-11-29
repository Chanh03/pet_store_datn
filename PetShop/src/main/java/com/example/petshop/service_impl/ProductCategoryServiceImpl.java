package com.example.petshop.service_impl;

import com.example.petshop.entity.Product;
import com.example.petshop.entity.ProductCategory;
import com.example.petshop.repo.ProductCategoryRepo;
import com.example.petshop.service.ProductCategoryService;
import com.example.petshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryRepo productCategoryRepo;

    @Autowired
    private ProductService productService;

    @Override
    public List<ProductCategory> getAll() {
        return productCategoryRepo.findAll();
    }

    @Override
    public ProductCategory getById(int id) {
        return productCategoryRepo.findById(id).orElse(null);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepo.save(productCategory);
    }

    @Override
    public void deleteById(int id) {
        productCategoryRepo.deleteById(id);
    }

    @Override
    public List<Product> getAllProductById(int id) {
        ProductCategory productCategory = productCategoryRepo.findById(id).orElse(null);
        return productService.getAllProductByCategoryId(productCategory);
    }
}
