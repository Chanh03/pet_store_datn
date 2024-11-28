package com.example.petshop.rest;

import com.example.petshop.entity.*;
import com.example.petshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RequestMapping("/api/review")
@RestController
public class RestReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderProductDetailService orderProductDetailService;

    @GetMapping
    public List<Review> getAll() {
        return reviewService.getAll();
    }

    @GetMapping("/{id}")
    public List<Review> getReviewsByProductId(@PathVariable int id) {
        return reviewService.getReviewsByProductId(id);
    }

    @PostMapping
    public Review addReview(@RequestBody Review review) {
        return reviewService.save(review);
    }
}