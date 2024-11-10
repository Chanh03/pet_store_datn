package com.example.petshop.controller;

import com.example.petshop.entity.Product;
import com.example.petshop.entity.ProductCategory;
import com.example.petshop.entity.Review;
import com.example.petshop.entity.Rating;
import com.example.petshop.service.ProductCategoryService;
import com.example.petshop.service.ProductService;
import com.example.petshop.service.ReviewService;
import com.example.petshop.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private RatingService ratingService;

	@Autowired
	private ProductCategoryService productCategoryService;

	@RequestMapping("/allProduct")
	public String viewProduct(Model model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(required = false) String search, @RequestParam(required = false) String sort) {
		int pageSize = 16;
		Page<Product> productPage;

		// Xử lý tìm kiếm
		if (search != null && !search.isEmpty()) {
			productPage = productService.searchProduct(search, PageRequest.of(page, pageSize,
					Sort.by(sort != null && sort.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, "price")));
		} else {
			productPage = productService.getPaginatedProduct(PageRequest.of(page, pageSize,
					Sort.by(sort != null && sort.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, "price")));
		}

		List<ProductCategory> categories = productCategoryService.getAll();
		model.addAttribute("productPage", productPage);
		model.addAttribute("categories", categories);
		model.addAttribute("search", search); 
		model.addAttribute("sort", sort);
		return "layout/_allProduct";
	}

	@RequestMapping("/product/detail/{id}")
	public String product(Model model, @PathVariable int id) {
	    Product product = productService.getById(id);

	    if (product != null) {
	        model.addAttribute("product", product);

	        // Lấy danh sách các sản phẩm cùng loại, bỏ qua sản phẩm hiện tại
	        List<Product> relatedProducts = productService.getProductsByCategory(product.getProductCategoryID().getId(), id);
	        model.addAttribute("relatedProducts", relatedProducts);

	        // Lấy danh sách sản phẩm, đánh giá và tính điểm trung bình
	        List<Review> reviews = reviewService.getReviewsByProductId(id);
	        model.addAttribute("reviews", reviews);
	        List<Rating> ratings = ratingService.getRatingsByProductId(id);
	        model.addAttribute("ratings", ratings);
	        double averageRating = ratingService.getAverageRatingByProductId(id);
	        model.addAttribute("averageRating", averageRating);
	    } else {
	        model.addAttribute("errorMessage", "Sản phẩm không tồn tại");
	        return "error";
	    }

	    return "layout/_productDetail";
	}
}