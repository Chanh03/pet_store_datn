package com.example.petshop.service;

import com.example.petshop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<Product> getAll();

    Product getById(int id);

    Product save(Product product);

    void deleteById(int id);

    Page<Product> searchProduct(String search, PageRequest of);

    Page<Product> getPaginatedProduct(PageRequest of);

    List<Product> getProductsByCategory(Integer id, int id1);

    List<Product> getAllByCreatedDate();

    List<Product> getAllByCreatedDateAndEnable();
}
