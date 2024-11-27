package com.example.petshop.controller;

import com.example.petshop.entity.Product;
import com.example.petshop.entity.ProductCategory;
import com.example.petshop.entity.Review;
import com.example.petshop.service.OrderProductDetailService;
import com.example.petshop.service.ProductCategoryService;
import com.example.petshop.service.ProductService;
import com.example.petshop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private OrderProductDetailService orderProductDetailService;

	@Autowired
	private ProductCategoryService productCategoryService;

	@RequestMapping("/allProduct")
	public String viewProduct(Model model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(required = false) String search, @RequestParam(required = false) String sort,
			@RequestParam(required = false) Integer categoryId, @RequestParam(required = false) Double minPrice,
			@RequestParam(required = false) Double maxPrice) {
		int pageSize = 16;
		Page<Product> productPage;

		// Xác định thứ tự sắp xếp
		Sort.Direction sortDirection = (sort != null && sort.equals("asc")) ? Sort.Direction.ASC : Sort.Direction.DESC;

		// Xử lý tìm kiếm theo khoảng giá
		if (minPrice != null && maxPrice != null) {
			// Tìm kiếm sản phẩm theo khoảng giá
			productPage = productService.searchProductWithPrice(sort, minPrice, maxPrice,
					PageRequest.of(page, pageSize, Sort.by(sortDirection, "price")));
		} else {
			// Xử lý kết hợp giữa tìm kiếm, danh mục, và sắp xếp nếu không có khoảng giá
			if (search != null && !search.isEmpty() && categoryId != null) {
				productPage = productService.searchProductWithCategory(search, categoryId,
						PageRequest.of(page, pageSize, Sort.by(sortDirection, "price")));
			} else if (search != null && !search.isEmpty()) {
				productPage = productService.searchProduct(search,
						PageRequest.of(page, pageSize, Sort.by(sortDirection, "price")));
			} else if (categoryId != null) {
				productPage = productService.getProductsByCategoryId(categoryId,
						PageRequest.of(page, pageSize, Sort.by(sortDirection, "price")));
			} else {
				productPage = productService
						.getPaginatedProduct(PageRequest.of(page, pageSize, Sort.by(sortDirection, "price")));
			}
		}

		List<ProductCategory> categories = productCategoryService.getAll();
		model.addAttribute("productPage", productPage);
		model.addAttribute("categories", categories);
		model.addAttribute("search", search);
		model.addAttribute("sort", sort);
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("minPrice", minPrice);
		model.addAttribute("maxPrice", maxPrice);

		return "layout/_allProduct";
	}

	@RequestMapping("/product/detail/{id}")
	public String product(Model model, @PathVariable int id) {
		Product product = productService.getById(id);

		if (product != null) {
			model.addAttribute("product", product);
			List<Product> relatedProducts = productService.getProductsByCategory(product.getProductCategoryID().getId(),
					id);
			model.addAttribute("relatedProducts", relatedProducts);

			List<Product> otherProducts = productService
					.getProductsByDifferentCategory(product.getProductCategoryID().getId(), product.getId());
			model.addAttribute("otherProducts", otherProducts);

			List<Review> reviews = reviewService.getReviewsByProductId(id);

			// Sắp xếp các đánh giá theo ngày (mới nhất lên trước)
			reviews.sort((r1, r2) -> r2.getReviewDate().compareTo(r1.getReviewDate())); // Sắp xếp theo ngày

			model.addAttribute("reviews", reviews != null ? reviews : List.of());

			List<Review> ratings = reviewService.getRatingsByProductId(id);
			model.addAttribute("ratings", ratings != null ? ratings : List.of());

			double averageRating = reviewService.getAverageRatingByProductId(id);
			model.addAttribute("averageRating", averageRating);
		} else {
			model.addAttribute("errorMessage", "Sản phẩm không tồn tại");
			return "error";
		}

		return "layout/_productDetail";
	}
}