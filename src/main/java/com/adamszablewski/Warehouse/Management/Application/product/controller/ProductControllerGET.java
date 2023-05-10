package com.adamszablewski.Warehouse.Management.Application.product.controller;

import com.adamszablewski.Warehouse.Management.Application.product.Product;
import com.adamszablewski.Warehouse.Management.Application.product.service.ProductServiceGetter;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class ProductControllerGET {

    ProductServiceGetter productService;

    @GetMapping("/products")
    public List<Product> retrieveAll(){
        return productService.retrieveAllProducts();
    }

    @GetMapping("/products/product-code/{product_code}")
    public Optional<Product> retrieveProductByProductCode(@PathVariable String product_code){
        return productService.retrieveProductByProductCode(product_code);
    }

    @GetMapping("/products/category/{category}")
    public List<Product> retrieveProductsByCategory(@PathVariable String category){
        return productService.retrieveProductByCategory(category);
    }

    @GetMapping("/products/product-name/{product_name}")
    public Optional<Product> retrieveProductByProductName(@PathVariable String product_name){
        return productService.retrieveProductByProductName(product_name);
    }

    @GetMapping("/products/brand/{brand}")
    public List<Product> retrieveProductsByBrand(@PathVariable String brand){
        return productService.retrieveProductByBrand(brand);
    }
}
