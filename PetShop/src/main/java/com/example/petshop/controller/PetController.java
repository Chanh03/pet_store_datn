package com.example.petshop.controller;

import com.example.petshop.entity.Pet;
import com.example.petshop.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class PetController {
    @Autowired
    private PetService petService;

    @RequestMapping("/pet")
    public String pet(Model model) {
        List<Pet> list = petService.getAll();
        model.addAttribute("list", list);
        return "/layout/_allPet";
    }

    @RequestMapping("/pet/detail/{id}")
    public String getProductById(@PathVariable("id") String id, Model model) {
        Pet pet = petService.findById(id);
        List<Pet> pets = petService.getAll();
        model.addAttribute("pet", pet);
        model.addAttribute("pets", pets);
        return "/layout/_petDetail";
    }

}
