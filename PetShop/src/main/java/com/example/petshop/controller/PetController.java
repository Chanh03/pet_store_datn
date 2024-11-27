package com.example.petshop.controller;

import com.example.petshop.entity.Pet;
import com.example.petshop.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class PetController {

	@Autowired
	private PetService petService;

	@RequestMapping("/pet")
	public String viewAllPets(Model model) {
		List<Pet> list = petService.getAll();
		model.addAttribute("list", list);
		return "layout/_allPet";
	}

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
		model.addAttribute("totalPages", petPage.getTotalPages());
		model.addAttribute("keyword", keyword);
		model.addAttribute("minPrice", minPrice);
		model.addAttribute("maxPrice", maxPrice);
		model.addAttribute("sort", priceOrder);

		return "layout/_allPet";
	}

	@RequestMapping("/pet/detail/{id}")
	public String viewPetDetail(Model model, @PathVariable String id) {
		Optional<Pet> optionalPet = Optional.ofNullable(petService.findById(id));

		if (optionalPet.isPresent()) {
			Pet pet = optionalPet.get();
			model.addAttribute("pet", pet);

			// Filter list of pets of the same category but different pet ID
			List<Pet> sameCategoryPets = petService.getAll().stream().filter(
					p -> !p.getPetID().equals(pet.getPetID()) && p.getPetCategoryID().equals(pet.getPetCategoryID()))
					.limit(12).collect(Collectors.toList());

			// Filter list of pets from different categories
			List<Pet> differentCategoryPets = petService.getAll().stream()
					.filter(p -> !p.getPetCategoryID().equals(pet.getPetCategoryID())).limit(12)
					.collect(Collectors.toList());

			model.addAttribute("pets", sameCategoryPets);
			model.addAttribute("petCates", differentCategoryPets);
		} else {
			model.addAttribute("errorMessage", "Thú cưng không tồn tại");
			return "error";
		}

		return "layout/_petDetail";
	}
}
