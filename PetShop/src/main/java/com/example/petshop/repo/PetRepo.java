package com.example.petshop.repo;

import com.example.petshop.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepo extends JpaRepository<Pet, String> {
	Page<Pet> findByPetDescriptionContainingIgnoreCase(String keyword, Pageable pageable);
}