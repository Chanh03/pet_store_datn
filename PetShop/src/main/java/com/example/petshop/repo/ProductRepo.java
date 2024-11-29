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
    @Query("SELECT p FROM Product p WHERE p.productName LIKE %:keyword% OR p.productDescription LIKE %:keyword%")
    Page<Product> searchByKeyword(String keyword, Pageable pageable);


    List<Product> findByProductCategoryID_IdAndIdNot(Integer id, int id1);

    @Query("SELECT p FROM Product p ORDER BY p.createDate DESC limit 6")
    List<Product> findAllByCreatedDateDesc();

    Page<Product> findByProductCategoryID_Id(Integer categoryId, Pageable pageable);

    @Query("SELECT p,r FROM Product p, Review r WHERE p.id = r.productID.id and p.available = true ORDER BY p.createDate DESC limit 8")
    List<Product> findAllByCreatedDateDescAndAvailable();
}