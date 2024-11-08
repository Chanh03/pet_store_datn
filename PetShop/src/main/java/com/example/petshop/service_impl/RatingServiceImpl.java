package com.example.petshop.service_impl;

import com.example.petshop.entity.Rating;
import com.example.petshop.repo.RatingRepo;
import com.example.petshop.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepo ratingRepo;

    @Override
    public List<Rating> getAll() {
        return ratingRepo.findAll();
    }

    @Override
    public Rating save(Rating rating) {
       return ratingRepo.save(rating);
    }
}
