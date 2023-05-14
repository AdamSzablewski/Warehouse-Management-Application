//package com.adamszablewski.Warehouse.Management.Application.product.service;
//
//import com.adamszablewski.Warehouse.Management.Application.product.Product;
//import com.adamszablewski.Warehouse.Management.Application.product.repository.ProductRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@AllArgsConstructor
//public class ProductServiceGetter {
//
//    ProductRepository productRepository;
//    public List<Product> retrieveAllProducts() {
//        return productRepository.findAll();
//    }
//
//    public Optional<Product> retrieveProductByProductCode(String product_code) {
//         return productRepository.findByProductCode(product_code);
//    }
//
//    public Optional<Product> retrieveProductByProductName(String productName) {
//        return productRepository.findByProductName(productName);
//    }
//
//    public List<Product> retrieveProductByCategory(String category) {
//        return productRepository.findAllByCategory(category);
//    }
//
//    public List<Product> retrieveProductByBrand(String brand) {
//       return productRepository.findAllByBrand(brand);
//    }
//}
