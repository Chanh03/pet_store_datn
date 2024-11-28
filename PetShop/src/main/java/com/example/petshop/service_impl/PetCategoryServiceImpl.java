package com.example.petshop.service_impl;

import com.example.petshop.entity.PetCategory;
import com.example.petshop.repo.PetCategoryRepo;
import com.example.petshop.service.PetCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetCategoryServiceImpl implements PetCategoryService {
    @Autowired
    private PetCategoryRepo petCategoryRepo;

    @Override
    public List<PetCategory> getAll() {
        return petCategoryRepo.findAll();
    }

    @Override
    public void save(PetCategory petCategory) {
        petCategoryRepo.save(petCategory);
    }

    @Override
    public void deleteId(int id) {
        petCategoryRepo.deleteById(id);
    }

    @Override
    public PetCategory findById(int id) {
        return petCategoryRepo.findById(id).orElse(null);
    }
}
