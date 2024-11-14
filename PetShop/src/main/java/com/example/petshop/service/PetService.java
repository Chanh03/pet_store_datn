package com.example.petshop.service;

import com.example.petshop.entity.Pet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PetService {
    List<Pet> getAll();

    Pet findById(String id);

    void save(Pet pet);

    void deleteById(String id);

    boolean existsById(String id);

    List<Pet> getAllByCreatedDate();
}
