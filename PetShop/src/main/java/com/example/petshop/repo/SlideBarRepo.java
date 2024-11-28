package com.example.petshop.repo;

import com.example.petshop.entity.SliderBar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlideBarRepo extends JpaRepository<SliderBar, Integer> {
}
