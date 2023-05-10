package com.adamszablewski.Warehouse.Management.Application.product.service;

import com.adamszablewski.Warehouse.Management.Application.product.Product;
import com.adamszablewski.Warehouse.Management.Application.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServicePost {

    ProductRepository productRepository;


    public ResponseEntity<String> createProduct(Product product) {
        productRepository.save(product);
        return ResponseEntity.ok("Product created");
    }
}
