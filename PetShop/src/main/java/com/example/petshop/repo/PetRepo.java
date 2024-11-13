package com.example.petshop.repo;

import com.example.petshop.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepo extends JpaRepository<Pet, String> {
    @Query("SELECT p FROM Pet p ORDER BY p.createDate DESC limit 6")
    List<Pet> findAllByCreatedDateDesc();
}
