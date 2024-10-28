package com.example.petshop.service;

import com.example.petshop.entity.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface PetService {
	List<Pet> getAll();

	Optional<Pet> findById(String id);

	void save(Pet pet);

	void deleteById(String id);

	boolean existsById(String id);

	Page<Pet> getPaginatedPets(Pageable pageable);

	Page<Pet> searchPets(String keyword, Pageable pageable);
}
