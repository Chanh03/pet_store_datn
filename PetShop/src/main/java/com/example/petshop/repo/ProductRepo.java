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

	@Query("""
			    SELECT p
			    FROM Product p
			    WHERE (:keyword IS NULL OR LOWER(p.productName) LIKE LOWER(CONCAT('%', :keyword, '%')))
			    AND (:categoryId IS NULL OR p.productCategoryID.id = :categoryId)
			""")
	Page<Product> searchProducts(String keyword, Integer categoryId, Pageable pageable);

	Page<Product> findByProductDescriptionContainingIgnoreCase(String keyword, Pageable pageable);

	Page<Product> findByProductCategoryID_Id(Integer categoryId, Pageable pageable);

	List<Product> findByProductCategoryID_IdAndIdNot(Integer categoryId, int excludeId);

	@Query("SELECT p FROM Product p ORDER BY p.createDate DESC")
	List<Product> findAllByCreatedDateDesc();
}
