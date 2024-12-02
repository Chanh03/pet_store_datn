package com.example.petshop.service;

import com.example.petshop.entity.Product;
import com.example.petshop.entity.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

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

    public List<Product> getProductsByDifferentCategory(int currentCategoryId, int currentProductId);


    Page<Product> getProductsByCategoryId(Integer categoryId, Pageable pageable);

    Page<Product> searchProductWithCategory(String keyword, Integer categoryId, Pageable pageable);

    List<Product> getAllByCreatedDateAndEnable();

    List<Product> getAllProductByCategoryId(ProductCategory productCategory);

    // Thêm phương thức tìm kiếm theo khoảng giá
    Page<Product> searchProductWithPrice(String search, Double minPrice, Double maxPrice, Pageable pageable);
}
