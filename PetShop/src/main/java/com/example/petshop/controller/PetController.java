package com.example.petshop.controller;

import com.example.petshop.entity.Pet;
import com.example.petshop.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PetController {
	@Autowired
	private PetService petService;

	@RequestMapping("/allPet")
	public String viewPets(Model model, 
	                       @RequestParam(defaultValue = "0") int page,
	                       @RequestParam(required = false) String keyword,
	                       @RequestParam(required = false) String priceOrder) {

	    int pageSize = 20;
	    Page<Pet> petPage;

	    // Xử lý tìm kiếm
	    if (keyword != null && !keyword.isEmpty()) {
	        petPage = petService.searchPets(keyword, 
	            PageRequest.of(page, pageSize, 
	                Sort.by(priceOrder != null && priceOrder.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, "price")));
	    } else {
	        petPage = petService.getPaginatedPets(
	            PageRequest.of(page, pageSize, 
	                Sort.by(priceOrder != null && priceOrder.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, "price")));
	    }

	    model.addAttribute("pets", petPage.getContent());
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", petPage.getTotalPages());
	    model.addAttribute("keyword", keyword);
	    model.addAttribute("sort", priceOrder);

	    return "layout/_allPet";
	}


	@RequestMapping("/pet/detail/{id}")
	public String petDetail(Model model, @PathVariable String id) {
	    Optional<Pet> optionalPet = petService.findById(id);

	    if (optionalPet.isPresent()) {
	        Pet pet = optionalPet.get();
	        model.addAttribute("pet", pet);

	        // Lọc các thú cưng khác theo cùng petCategoryID
	        List<Pet> otherPets = petService.getAll().stream()
	            .filter(p -> !p.getPetID().equals(pet.getPetID()) && p.getPetCategoryID().equals(pet.getPetCategoryID()))
	            .limit(12)
	            .collect(Collectors.toList());
	        model.addAttribute("pets", otherPets);
	    } else {
	        model.addAttribute("errorMessage", "Thú cưng không tồn tại");
	        return "error";
	    }

	    return "layout/_petDetail";
	}

}