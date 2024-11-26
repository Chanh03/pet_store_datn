package com.example.petshop.repo;

import com.example.petshop.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface PetRepo extends JpaRepository<Pet, String> {

	// Lấy danh sách Pet mới nhất
	@Query("SELECT p FROM Pet p ORDER BY p.createDate DESC")
	List<Pet> findAllByCreatedDateDesc();

	// Tìm kiếm linh hoạt với keyword
	@Query("SELECT p FROM Pet p WHERE " + "p.breed LIKE CONCAT('%', :keyword, '%') OR "
			+ "p.famous LIKE CONCAT('%', :keyword, '%') OR " + "p.hair LIKE CONCAT('%', :keyword, '%')")
	Page<Pet> searchByKeyword(@org.springframework.data.repository.query.Param("keyword") String keyword,
			Pageable pageable);
}
