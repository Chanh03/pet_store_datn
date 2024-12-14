package com.example.petshop.rest;

import com.example.petshop.entity.Pet;
import com.example.petshop.service.PetCategoryService;
import com.example.petshop.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin("*")
@RequestMapping("/api/pet")
@RestController
public class RestPetController {
    @Autowired
    private PetService petService;
    @Autowired
    private PetCategoryService petCategoryService;

    @GetMapping
    public List<Pet> getPet() {
        return petService.getAll();
    }

    @GetMapping("/{id}")
    public Pet getPetId(@PathVariable("id") String id) {
        return petService.findById(id);
    }

    @PostMapping
    public void save(@RequestBody Pet pet) {
        pet.setCreateDate(LocalDateTime.now());
        pet.setAvailable(true);
        pet.setEnable(true);
        petService.save(pet);
    }

    @PutMapping("/{id}")
    public void updatePet(@PathVariable("id") String id, @RequestBody Pet pet) {
        petService.save(pet);
    }

    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable("id") String id) {
        petService.deleteById(id);
    }

    @PutMapping("/{id}/enable/{enable}")
    public void enablePet(@PathVariable("id") String id, @PathVariable("enable") boolean enable) {
        Pet pet = petService.findById(id);
        pet.setEnable(enable);
        petService.save(pet);
    }

    @PutMapping("/{id}/available/{available}")
    public void availablePet(@PathVariable("id") String id, @PathVariable("available") boolean available) {
        Pet pet = petService.findById(id);
        pet.setAvailable(available);
        petService.save(pet);
    }

    @PutMapping("/{id}/price/{price}")
    public void updatePrice(@PathVariable("id") String id, @PathVariable("price") int price) {
        Pet pet = petService.findById(id);
        pet.setPrice(price);
        petService.save(pet);
    }
}
