package com.adamszablewski.Warehouse.Management.Application.product.controller;

import com.adamszablewski.Warehouse.Management.Application.product.Product;
import com.adamszablewski.Warehouse.Management.Application.product.service.ProductServicePut;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductControllerPUT {

    ProductServicePut productServicePut;

    @PutMapping("/products/id/{product_id}")
    public ResponseEntity<String> updateProduct(@PathVariable int product_id, @RequestBody Product product){
        return productServicePut.updateProduct(product, product_id);
    }
}
