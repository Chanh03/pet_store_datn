package com.example.petshop.rest;

import com.example.petshop.entity.Pet;
import com.example.petshop.entity.PetCategory;
import com.example.petshop.service.PetCategoryService;
import com.example.petshop.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/api/pet-category")
@RestController
public class RestPetCategoryController {
    @Autowired
    PetCategoryService petCategoryService;

    @Autowired
    private PetService petService;

    @GetMapping
    public List<PetCategory> getAll() {
        return petCategoryService.getAll();
    }

    @GetMapping("/{id}")
    public void getID(@PathVariable("id") int id) {
        petCategoryService.findById(id);
    }

    @PostMapping
    public void save(@RequestBody PetCategory petCategory) {
        petCategoryService.save(petCategory);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody PetCategory petCategory) {
        petCategory.setId(id);
        petCategoryService.save(petCategory);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        petCategoryService.deleteId(id);
    }

    @GetMapping("/{id}/all-pet")
    public List<Pet> getAllPetById(@PathVariable("id") int id) {
        PetCategory petCategoryId = petCategoryService.findById(id);
        return petService.getAllPetByCategoryId(petCategoryId);
    }
}
