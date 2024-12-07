package com.example.petshop.service_impl;

import com.example.petshop.entity.Product;
import com.example.petshop.entity.ProductCategory;
import com.example.petshop.repo.ProductRepo;
import com.example.petshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<Product> getAll() {
        return productRepo.findAll();
    }

    @Override
    public Product getById(int id) {
        return productRepo.findById(id).orElse(null);
    }

    @Override
    public Product save(Product product) {
        return productRepo.save(product);
    }

    @Override
    public void deleteById(int id) {
        productRepo.deleteById(id);
    }

    @Override
    public Page<Product> searchProduct(String search, PageRequest of) {
        return productRepo.searchByKeyword(search, of);
    }

    @Override
    public Page<Product> getPaginatedProduct(PageRequest of) {
        return productRepo.findAll(of);
    }

    @Override
    public List<Product> getProductsByCategory(Integer id, int id1) {
        return productRepo.findByProductCategoryID_IdAndIdNot(id, id1);
    }

    @Override
    public List<Product> getAllByCreatedDate() {
        return productRepo.findAllByCreatedDateDesc();
    }

    @Override
    public Page<Product> getProductsByCategoryId(Integer categoryId, Pageable pageable) {
        return productRepo.findByProductCategoryID_Id(categoryId, pageable);
    }

    @Override
    public List<Product> getProductsByDifferentCategory(int currentCategoryId, int currentProductId) {
        // Lấy tất cả sản phẩm, sau đó lọc để chỉ giữ sản phẩm khác loại và không trùng ID
        return productRepo.findAll().stream()
                .filter(product -> !product.getProductCategoryID().getId().equals(currentCategoryId)) // Khác loại
                .filter(product -> !product.getId().equals(currentProductId)) // Không trùng ID
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> getAllByCreatedDateAndEnable() {
        return productRepo.findAllByAndAvailableCreatedDateDesc();
    }

    @Override
    public List<Product> getAllProductByCategoryId(ProductCategory productCategory) {
        return productRepo.findByProductCategoryID(productCategory);
    }

    @Override
    public Page<Product> searchProductByPriceAndKeyword(String search, Double minPrice, Double maxPrice, PageRequest price) {
        return productRepo.searchByPriceAndKeyword(search, minPrice, maxPrice, price);
    }

    @Override
    public Page<Product> searchProductByPriceRange(Double minPrice, Double maxPrice, PageRequest price) {
        return productRepo.searchByPriceRange(minPrice, maxPrice, price);
    }
}
