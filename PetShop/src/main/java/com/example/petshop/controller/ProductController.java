package com.example.petshop.controller;

import com.example.petshop.entity.Product;
import com.example.petshop.entity.Review;
import com.example.petshop.entity.Rating;
import com.example.petshop.service.ProductService;
import com.example.petshop.service.ReviewService;
import com.example.petshop.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private RatingService ratingService;

    @RequestMapping("/allProduct")
    public String register() {
        return "layout/_allProduct";
    }
    
    @RequestMapping("/product/detail/{id}")
    public String product(Model model, @PathVariable int id) {
        Product product = productService.getById(id);

        if (product != null) {
            model.addAttribute("product", product);

            List<Product> products = productService.getAll();
            model.addAttribute("products", products);

            // Lấy danh sách đánh giá cho sản phẩm
            List<Review> reviews = reviewService.getReviewsByProductId(id);
            model.addAttribute("reviews", reviews);

            // Lấy danh sách đánh giá sao cho sản phẩm
            List<Rating> ratings = ratingService.getRatingsByProductId(id);
            model.addAttribute("ratings", ratings);

            // Tính điểm trung bình
            double averageRating = ratingService.getAverageRatingByProductId(id);
            model.addAttribute("averageRating", averageRating);
        } else {
            model.addAttribute("errorMessage", "Sản phẩm không tồn tại");
            return "error"; // Giả sử bạn có một trang lỗi
        }

        return "/layout/_productDetail";
    }

}
