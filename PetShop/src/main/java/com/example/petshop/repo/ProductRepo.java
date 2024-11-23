package com.example.petshop.repo;

import com.example.petshop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    // Tìm kiếm sản phẩm theo mô tả, phân trang
    Page<Product> findByProductDescriptionContainingIgnoreCase(String keyword, Pageable pageable);

    // Tìm sản phẩm theo danh mục và id sản phẩm khác
    List<Product> findByProductCategoryID_IdAndIdNot(Integer categoryId, int productId);

    // Lấy các sản phẩm mới nhất (phân trang có thể được thêm nếu cần)
    @Query("SELECT p FROM Product p ORDER BY p.createDate DESC")
    List<Product> findAllByCreatedDateDesc();

    // Lấy các sản phẩm mới nhất và có sẵn
    @Query("SELECT p FROM Product p WHERE p.available = true ORDER BY p.createDate DESC")
    List<Product> findAllByCreatedDateDescAndAvailable();

    // Lấy sản phẩm theo danh mục, phân trang
    Page<Product> findByProductCategoryID_Id(Integer categoryId, Pageable pageable);

    // Tìm kiếm sản phẩm theo mô tả và danh mục, phân trang
    Page<Product> findByProductDescriptionAndProductCategoryID_Id(String keyword, Integer categoryId, Pageable pageable);

    // Tìm kiếm sản phẩm theo mô tả, danh mục và phân trang
    Page<Product> findByProductCategoryID_IdAndProductDescriptionContainingIgnoreCase(Integer categoryId, String keyword, Pageable pageable);
}
