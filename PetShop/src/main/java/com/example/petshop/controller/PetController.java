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

        // Sắp xếp theo giá (mặc định giảm dần)
        Sort sort = Sort.by("price").descending();
        if ("asc".equalsIgnoreCase(priceOrder)) {
            sort = Sort.by("price").ascending();
        }

        // Xử lý tìm kiếm
        if (keyword != null && !keyword.trim().isEmpty()) {
            petPage = petService.searchPets(keyword.trim(), PageRequest.of(page, pageSize, sort));
        } else {
            petPage = petService.getPaginatedPets(PageRequest.of(page, pageSize, sort));
        }

        // Thêm dữ liệu vào model
        model.addAttribute("pets", petPage.getContent());
        model.addAttribute("petPage", petPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", petPage.getTotalPages());
        model.addAttribute("keyword", keyword);
        model.addAttribute("sort", priceOrder);

        return "layout/_allPet";
    }

    @RequestMapping("/pet/detail/{id}")
    public String petDetail(Model model, @PathVariable String id) {
        Optional<Pet> optionalPet = Optional.ofNullable(petService.findById(id));

        if (optionalPet.isPresent()) {
            Pet pet = optionalPet.get();
            model.addAttribute("pet", pet);

            // Lọc danh sách thú cưng cùng loại (cùng `petCategoryID`) nhưng không trùng
            // `petID`
            List<Pet> otherPets = petService.getAll().stream().filter(
                            p -> !p.getPetID().equals(pet.getPetID()) && p.getPetCategoryID().equals(pet.getPetCategoryID()))
                    .limit(12).collect(Collectors.toList());
            List<Pet> otherPetCate = petService.getAll().stream().filter(
                            p -> !p.getPetCategoryID().equals(pet.getPetCategoryID()))
                    .limit(12).collect(Collectors.toList());
            model.addAttribute("pets", otherPets);
            model.addAttribute("petCates", otherPetCate);
        } else {
            model.addAttribute("errorMessage", "Thú cưng không tồn tại");
            return "error";
        }

        return "layout/_petDetail";
    }
}
