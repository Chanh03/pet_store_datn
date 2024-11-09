package com.example.petshop.service_impl;

import com.example.petshop.entity.Product;
import com.example.petshop.repo.ProductRepo;
import com.example.petshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return productRepo.findByProductDescriptionContainingIgnoreCase(search, of);
    }

    @Override
    public Page<Product> getPaginatedProduct(PageRequest of) {
        return productRepo.findAll(of);
    }

    @Override
    public List<Product> getProductsByCategory(Integer id, int id1) {
        return productRepo.findByProductCategoryID_IdAndIdNot(id, id1);
    }

}
