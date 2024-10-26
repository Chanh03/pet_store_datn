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

@Controller
public class PetController {
    @Autowired
    private PetService petService;
    
    @RequestMapping("/allPet")
    public String register() {
        return "layout/_allPet";
    }

    @RequestMapping("/pet/detail/{id}")
    public String petDetail(Model model, @PathVariable String id) {
        Optional<Pet> optionalPet = petService.findById(id);

        // Kiểm tra xem thú cưng có tồn tại hay không
        if (optionalPet.isPresent()) {
            Pet pet = optionalPet.get();
            model.addAttribute("pet", pet);

            List<Pet> otherPets = petService.getAll()
                    .stream()
                    .filter(p -> !p.getPetID().equals(pet.getPetID())) // Lọc bỏ thú cưng hiện tại
                    .limit(12) // Giới hạn số lượng thú cưng khác hiển thị
                    .collect(Collectors.toList());
            model.addAttribute("pets", otherPets);
        } else {
            model.addAttribute("errorMessage", "Thú cưng không tồn tại");
            return "error"; // Giả sử bạn có một trang lỗi
        }

        return "/layout/_petDetail";
    }
}