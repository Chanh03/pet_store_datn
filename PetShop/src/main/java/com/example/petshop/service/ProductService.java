package com.example.petshop.service;

import com.example.petshop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    // Lấy tất cả sản phẩm
    List<Product> getAll();

    // Lấy sản phẩm theo id
    Product getById(int id);

    // Lưu sản phẩm
    Product save(Product product);

    // Xóa sản phẩm theo id
    void deleteById(int id);

    // Tìm kiếm sản phẩm theo mô tả với phân trang
    Page<Product> searchProduct(String search, PageRequest of);

    // Lấy sản phẩm phân trang
    Page<Product> getPaginatedProduct(PageRequest of);

    // Lấy sản phẩm theo danh mục và loại trừ sản phẩm theo id
    List<Product> getProductsByCategory(Integer id, int id1);

    // Lấy tất cả sản phẩm theo ngày tạo (không phân trang)
    List<Product> getAllByCreatedDate();

    // Lấy tất cả sản phẩm có sẵn theo ngày tạo (không phân trang)
    List<Product> getAllByCreatedDateAndEnable();

    // Lấy sản phẩm theo danh mục và phân trang
    Page<Product> getProductsByCategoryId(Integer categoryId, Pageable pageable);

    // Tìm kiếm sản phẩm theo mô tả và danh mục với phân trang
    Page<Product> searchProductWithCategory(String keyword, Integer categoryId, Pageable pageable);

    // Lấy sản phẩm theo danh mục khác với sản phẩm hiện tại (để tìm sản phẩm liên quan)
    List<Product> getProductsByDifferentCategory(int currentCategoryId, int currentProductId);

    // Lấy 8 sản phẩm mới nhất
    List<Product> getTopNewProducts(int limit);
}
