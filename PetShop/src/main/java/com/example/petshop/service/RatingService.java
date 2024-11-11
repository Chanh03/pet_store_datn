package com.example.petshop.service;

import com.example.petshop.entity.Rating;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RatingService {
    List<Rating> getAll();

    Rating save(Rating rating);

    List<Rating> getRatingsByProductId(int id);

    double getAverageRatingByProductId(int id);
}
