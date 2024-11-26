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
	public String viewPets(Model model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(required = false) String keyword, @RequestParam(defaultValue = "desc") String priceOrder) {

		int pageSize = 24; // Số thú cưng trên mỗi trang
		Page<Pet> petPage;

		// Sắp xếp theo giá
		Sort sort = Sort.by("price").descending();
		if ("asc".equalsIgnoreCase(priceOrder)) {
			sort = Sort.by("price").ascending();
		}

		// Tìm kiếm theo keyword nếu có
		if (keyword != null && !keyword.trim().isEmpty()) {
			petPage = petService.searchPets(keyword.trim(), PageRequest.of(page, pageSize, sort));
		} else {
			petPage = petService.getPaginatedPets(PageRequest.of(page, pageSize, sort));
		}

		// Gắn dữ liệu vào model
		model.addAttribute("pets", petPage.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", petPage.getTotalPages());
		model.addAttribute("keyword", keyword);
		model.addAttribute("sort", priceOrder);

		return "layout/_allPet";
	}

	@RequestMapping("/pet/detail/{id}")
	public String petDetail(Model model, @PathVariable String id) {
		Pet pet = petService.findById(id);
		if (pet != null) {
			model.addAttribute("pet", pet);

			// Lấy danh sách các thú cưng liên quan
			List<Pet> otherPets = petService.getAll().stream().filter(
					p -> !p.getPetID().equals(pet.getPetID()) && p.getPetCategoryID().equals(pet.getPetCategoryID()))
					.limit(12).toList();
			model.addAttribute("pets", otherPets);
		} else {
			model.addAttribute("errorMessage", "Thú cưng không tồn tại");
			return "error";
		}

		return "layout/_petDetail";
	}
}
