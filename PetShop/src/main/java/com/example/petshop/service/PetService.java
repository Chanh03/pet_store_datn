package com.example.petshop.service;

import com.example.petshop.entity.Pet;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public interface PetService {
	List<Pet> getAll();

	Pet findById(String id);

	void save(Pet pet);

	void deleteById(String id);

	boolean existsById(String id);

	List<Pet> getAllByCreatedDate(); // Lấy danh sách theo ngày tạo

	Page<Pet> getPaginatedPets(Pageable pageable); // Phân trang Pet

	Page<Pet> searchPets(String keyword, Pageable pageable); // Tìm kiếm Pet
}
