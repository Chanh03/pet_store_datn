package com.example.petshop.rest;

import com.example.petshop.entity.Pet;
import com.example.petshop.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/pet")
@CrossOrigin("*")
public class RestPetController {

	@Autowired
	private PetService petService;

	@GetMapping
	public List<Pet> getPets() {
		return petService.getAll();
	}

	@GetMapping("/{id}")
	public Pet getPetById(@PathVariable("id") String id) {
		return petService.findById(id);
	}

	@PostMapping
	public void savePet(@RequestBody Pet pet) {
		pet.setCreateDate(LocalDateTime.now());
		petService.save(pet);
	}

	@PutMapping("/{id}")
	public void updatePet(@PathVariable("id") String id, @RequestBody Pet pet) {
		pet.setPetID(id);
		petService.save(pet);
	}

	@DeleteMapping("/{id}")
	public void deletePet(@PathVariable("id") String id) {
		petService.deleteById(id);
	}
}
