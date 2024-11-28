package com.example.petshop.repo;

import com.example.petshop.entity.BookingService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingServiceRepo extends JpaRepository<BookingService, Integer> {
}
