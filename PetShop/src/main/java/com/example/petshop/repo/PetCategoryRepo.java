package com.example.petshop.repo;

import com.example.petshop.entity.PetCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetCategoryRepo extends JpaRepository<PetCategory, Integer> {

}
