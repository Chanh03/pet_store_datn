package com.example.petshop.rest;

import com.example.petshop.entity.Product;
import com.example.petshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin("*")
@RequestMapping("/api/product")
@RestController
public class RestProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getProducts() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return productService.getById(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        if (product.getQuantity() == 0 || product.getQuantity() < 0) {
            product.setQuantity(0);
            product.setAvailable(false);
        }
        if (product.getQuantity() > 0) {
            product.setAvailable(true);
        }
        product.setCreateDate(LocalDateTime.now());
        return productService.save(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        if (product.getQuantity() == 0 || product.getQuantity() < 0) {
            product.setQuantity(0);
            product.setAvailable(false);
        }
        if (product.getQuantity() > 0) {
            product.setAvailable(true);
        }
        product.setId(id);
        return productService.save(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.deleteById(id);
    }
}