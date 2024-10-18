package com.example.petshop.controller;

import com.example.petshop.entity.Pet;
import com.example.petshop.entity.PetCategory;
import com.example.petshop.service.PetCategoryService;
import com.example.petshop.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RequestMapping("/api/pet")
@RestController
public class PetController {
    @Autowired
    private PetService petService;
    @Autowired
    private PetCategoryService petCategoryService;

    @GetMapping
    public List<Pet> getPet() {
        return petService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Pet> getPetId(@PathVariable("id") String id) {
        return petService.findById(id);
    }

    @PostMapping
    public void save(@RequestBody Pet pet) {
        PetCategory category = petCategoryService.findById(pet.getPetCategoryID().getId());
        pet.setPetCategoryID(category);
        petService.save(pet);
    }

    @PutMapping("/{id}")
    public void updatePet(@PathVariable("id") String id, @RequestBody Pet pet) {
        pet.setPetID(id);
        petService.save(pet);
    }

    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable("id") String id) {
        petService.deleteById(id);
    }
}
