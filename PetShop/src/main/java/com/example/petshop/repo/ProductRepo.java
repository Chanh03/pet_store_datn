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
    Page<Product> findByProductDescriptionContainingIgnoreCase(String keyword, Pageable pageable);

    List<Product> findByProductCategoryID_IdAndIdNot(Integer id, int id1);

    @Query("SELECT p FROM Product p ORDER BY p.createDate DESC limit 8")
    List<Product> findAllByCreatedDateDesc();

    @Query("SELECT p FROM Product p WHERE p.available = true ORDER BY p.createDate DESC limit 8")
    List<Product> findAllByCreatedDateDescAndAvailable();
}
