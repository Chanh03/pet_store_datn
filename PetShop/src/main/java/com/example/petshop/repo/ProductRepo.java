package com.example.petshop.repo;

import com.example.petshop.entity.Product;
import com.example.petshop.entity.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    List<Product> findByProductCategoryID_IdAndIdNot(Integer id, int id1);

    @Query("SELECT p FROM Product p ORDER BY p.createDate DESC limit 6")
    List<Product> findAllByCreatedDateDesc();

    @Query("SELECT p FROM Product p WHERE p.available = true ORDER BY p.createDate DESC limit 8")
    List<Product> findAllByAndAvailableCreatedDateDesc();

    List<Product> findByProductCategoryID(ProductCategory productCategory);

    Page<Product> findByProductCategoryID_Id(Integer categoryId, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.productName LIKE %:keyword% OR p.productDescription LIKE %:keyword%")
    Page<Product> searchByKeyword(String keyword, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice")
    Page<Product> searchByPriceRange(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE (p.productName LIKE %:keyword% OR p.productDescription LIKE %:keyword%) AND p.price BETWEEN :minPrice AND :maxPrice")
    Page<Product> searchByPriceAndKeyword(@Param("keyword") String keyword,
                                          @Param("minPrice") Double minPrice,
                                          @Param("maxPrice") Double maxPrice,
                                          Pageable pageable);
}
