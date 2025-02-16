package com.example.petshop.service;

import com.example.petshop.entity.Pet;
import com.example.petshop.entity.PetCategory;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public interface PetService {
    List<Pet> getAll();

    Pet findById(String id);

    void save(Pet pet);

    void deleteById(String id);

    boolean existsById(String id);

    List<Pet> getAllByCreatedDate();

    Page<Pet> getPaginatedPets(Pageable pageable);

    Page<Pet> searchPets(String keyword, Pageable pageable);

    List<Pet> getAllPetByCategoryId(PetCategory id);

    Page<Pet> searchPetsByPriceRange(Integer minPrice, Integer maxPrice, Pageable pageable);
    Page<Pet> searchPetByPriceAndKeyword(String keyword, Integer minPrice, Integer maxPrice, Pageable pageable);
    Page<Pet> findByPetCategory(String categoryId, Pageable pageable);
}