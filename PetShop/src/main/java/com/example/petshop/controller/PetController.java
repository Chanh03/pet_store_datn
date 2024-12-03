package com.example.petshop.controller;

import com.example.petshop.entity.Pet;
import com.example.petshop.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class PetController {

    @Autowired
    private PetService petService;

    @RequestMapping("/allPet")
    public String viewPaginatedPets(Model model, @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(required = false) String keyword, @RequestParam(defaultValue = "desc") String priceOrder,
                                    @RequestParam(required = false) Integer minPrice, @RequestParam(required = false) Integer maxPrice) {

        int pageSize = 24;
        Page<Pet> petPage;

        Sort sort = Sort.by("price").descending();
        if ("asc".equalsIgnoreCase(priceOrder)) {
            sort = Sort.by("price").ascending();
        }

        if (keyword != null && !keyword.trim().isEmpty()) {
            petPage = petService.searchPets(keyword.trim(), PageRequest.of(page, pageSize, sort));
        } else {
            petPage = petService.getPaginatedPets(PageRequest.of(page, pageSize, sort));
        }

        if (minPrice != null && maxPrice != null) {
            petPage = petService.searchPetsByPriceRange(minPrice, maxPrice, PageRequest.of(page, pageSize, sort));
        }
        model.addAttribute("pets", petPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("petPage", petPage);
        model.addAttribute("totalPages", petPage.getTotalPages());
        model.addAttribute("keyword", keyword);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("sort", priceOrder);

        return "layout/_allPet";
    }

    @RequestMapping("/pet/detail/{id}")
    public String petDetail(Model model, @PathVariable String id) {
        List<Pet> pet = petService.getAll();
        Pet pet1 = petService.findById(id);
        List<Pet> samePetCategory = petService.getAllPetByCategoryId(pet1.getPetCategoryID());
        List<Pet> otherPetCategory = pet.stream().filter(p -> !p.getPetCategoryID().equals(pet1.getPetCategoryID())).collect(Collectors.toList());
        model.addAttribute("pet", pet1);
        model.addAttribute("samePetCategory", samePetCategory);
        model.addAttribute("otherPetCategory", otherPetCategory);
        return "layout/_petDetail";
    }
}
