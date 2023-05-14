package com.adamszablewski.Warehouse.Management.Application.product.controller;

import com.adamszablewski.Warehouse.Management.Application.product.Product;

import com.adamszablewski.Warehouse.Management.Application.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductControllerPOST {

    ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<String> createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }
}
