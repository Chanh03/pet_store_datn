package com.example.petshop.service;

import com.example.petshop.entity.Rating;
import java.util.List;

public interface RatingService {
    List<Rating> getRatingsByProductId(int productId);
}
