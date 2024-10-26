package com.example.petshop.service_impl;

import com.example.petshop.entity.Pet;
import com.example.petshop.repo.PetRepo;
import com.example.petshop.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetServiceImpl implements PetService {
    @Autowired
    private PetRepo petRepo;

    @Override
    public List<Pet> getAll() {
        return petRepo.findAll();
    }

    @Override
    public Optional<Pet> findById(String id) {
        return petRepo.findById(id);
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
}
