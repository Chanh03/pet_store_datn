package com.example.petshop.service;

import com.example.petshop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    List<Product> getProductsByDifferentCategory(int currentCategoryId, int currentProductId);

    List<Product> getAllByCreatedDate();

    Page<Product> getProductsByCategoryId(Integer categoryId, Pageable pageable);

    Page<Product> searchProductWithCategory(String keyword, Integer categoryId, Pageable pageable);

    List<Product> getAllByCreatedDateAndEnable();

    // Thêm phương thức tìm kiếm theo khoảng giá
    Page<Product> searchProductWithPrice(String search, Double minPrice, Double maxPrice, Pageable pageable);

    Page<Product> getProductsByPriceRange(Double minPrice, Double maxPrice, Pageable pageable);

    Page<Product> searchProductWithCategoryAndPrice(String keyword, Integer categoryId, Double minPrice, Double maxPrice, Pageable pageable);

    Page<Product> getProductsByCategoryAndPrice(Integer categoryId, Double minPrice, Double maxPrice, Pageable pageable);
}
