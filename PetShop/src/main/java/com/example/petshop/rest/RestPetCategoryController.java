package com.example.petshop.rest;

import com.example.petshop.entity.PetCategory;
import com.example.petshop.service.PetCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/api/pet-category")
@RestController
public class RestPetCategoryController {
    @Autowired
    PetCategoryService petCategoryService;


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
}
