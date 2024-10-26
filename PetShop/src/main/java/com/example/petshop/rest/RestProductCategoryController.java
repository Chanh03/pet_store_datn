package com.example.petshop.rest;

import com.example.petshop.entity.ProductCategory;
import com.example.petshop.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/api/product-category")
@RestController
public class RestProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping
    public List<ProductCategory> getAll() {
        return productCategoryService.getAll();
    }

    @GetMapping("/{id}")
    public ProductCategory getById(@PathVariable int id) {
        return productCategoryService.getById(id);
    }

    @PostMapping
    public ProductCategory save(@RequestBody ProductCategory productCategory) {
        return productCategoryService.save(productCategory);
    }

    @PutMapping("/{id}")
    public ProductCategory update(@PathVariable int id, @RequestBody ProductCategory productCategory) {
        productCategory.setId(id);
        return productCategoryService.save(productCategory);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        productCategoryService.deleteById(id);
    }
}
