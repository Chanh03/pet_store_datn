package com.example.petshop.repo;

import com.example.petshop.entity.Pet;
import com.example.petshop.entity.PetCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface PetRepo extends JpaRepository<Pet, String> {
    @Query("SELECT p FROM Pet p where p.available = true ORDER BY p.createDate DESC limit 6")
    List<Pet> findAllByCreatedDateDesc();

    Page<Pet> findByPetDescriptionContainingIgnoreCase(String keyword, Pageable pageable);

    List<Pet> findAllByPetCategoryID(PetCategory petCategoryID);
    // Tìm kiếm thú cưng theo khoảng giá
    Page<Pet> findByPriceBetween(Integer minPrice, Integer maxPrice, Pageable pageable);
@Query("SELECT p FROM Pet p WHERE p.price >= :minPrice AND p.price <= :maxPrice ORDER BY p.price ASC")
List<Pet> findAllByPriceRangeOrderByPriceAsc(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);
  
   
}
