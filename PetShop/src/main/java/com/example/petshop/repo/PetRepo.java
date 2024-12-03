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

    Page<Pet> findByPriceBetween(Integer minPrice, Integer maxPrice, Pageable pageable);
    @Query("SELECT p FROM Pet p WHERE (p.petID LIKE %:keyword% OR p.petDescription LIKE %:keyword%) AND p.price BETWEEN :minPrice AND :maxPrice")
    Page<Pet> searchByPriceAndKeyword(@Param("keyword") String keyword,
                                       @Param("minPrice") Integer minPrice,
                                       @Param("maxPrice") Integer maxPrice,
                                       Pageable pageable);


}
