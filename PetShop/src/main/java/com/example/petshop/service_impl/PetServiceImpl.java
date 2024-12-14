package com.example.petshop.service_impl;

import com.example.petshop.entity.Pet;
import com.example.petshop.entity.PetCategory;
import com.example.petshop.repo.PetRepo;
import com.example.petshop.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {
    @Autowired
    private PetRepo petRepo;

    @Override
    public List<Pet> getAll() {
        return petRepo.findAll();
    }

    @Override
    public Pet findById(String id) {
        return petRepo.findById(id).orElse(null);
    }

    @Override
    public void save(Pet pet) {
        petRepo.save(pet);
    }

    @Override
    public void deleteById(String id) {
        petRepo.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return petRepo.existsById(id);
    }

    @Override
    public List<Pet> getAllByCreatedDate() {
        return petRepo.findAllByCreatedDateDesc();
    }

    @Override
    public Page<Pet> getPaginatedPets(Pageable pageable) {
        return petRepo.findAll(pageable);
    }

    @Override
    public Page<Pet> searchPets(String keyword, Pageable pageable) {
        return petRepo.searchByKeyword(keyword, pageable);
    }

    @Override
    public List<Pet> getAllPetByCategoryId(PetCategory id) {
        return petRepo.findAllByPetCategoryID(id);
    }

    @Override
    public Page<Pet> searchPetsByPriceRange(Integer minPrice, Integer maxPrice, Pageable pageable) {
        return petRepo.findByPriceBetween(minPrice, maxPrice, pageable);
    }

    @Override
    public Page<Pet> searchPetByPriceAndKeyword(String keyword, Integer minPrice, Integer maxPrice, Pageable pageable) {
        return petRepo.searchByPriceAndKeyword(keyword, minPrice, maxPrice, pageable);
    }

    @Override
    public Page<Pet> findByPetCategory(String categoryId, Pageable pageable) {
        return petRepo.findByPetCategory(categoryId, pageable);
    }
}