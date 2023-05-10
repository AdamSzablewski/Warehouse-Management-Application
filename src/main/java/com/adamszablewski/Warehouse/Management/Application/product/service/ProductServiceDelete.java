package com.adamszablewski.Warehouse.Management.Application.product.service;

import com.adamszablewski.Warehouse.Management.Application.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProductServiceDelete {

    ProductRepository productRepository;


    public ResponseEntity<String> removeProductById(int product_id) {
        if(productRepository.findById(product_id).isPresent()) {
            productRepository.deleteById(product_id);
            return ResponseEntity.ok("Product deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no program with such ID");
    }


    public ResponseEntity<String> removeProductByName(String product_name) {
        if(productRepository.findByProductName(product_name).isPresent()) {
            productRepository.deleteByProductName(product_name);
            return ResponseEntity.ok("Product deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no program with such name");
    }

    public ResponseEntity<String> removeProductByProductCode(String productCode) {
        if(productRepository.findByProductCode(productCode).isPresent()) {
            productRepository.deleteByProductCode(productCode);
            return ResponseEntity.ok("Product deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no program with such product code");
    }
}
