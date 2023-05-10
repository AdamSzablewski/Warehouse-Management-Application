package com.adamszablewski.Warehouse.Management.Application.product.controller;

import com.adamszablewski.Warehouse.Management.Application.product.service.ProductServiceDelete;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ProductControllerDELETE {

    ProductServiceDelete productServiceDelete;

    @DeleteMapping("/products/id/{product_id}")
    public ResponseEntity<String> removeProductById(@PathVariable int product_id){
        return productServiceDelete.removeProductById(product_id);
    }

    @DeleteMapping("/products/product-name/{product_name}")
    public ResponseEntity<String> removeProductByName(@PathVariable String product_name){
        return productServiceDelete.removeProductByName(product_name);
    }

    @DeleteMapping("/products/product-code/{productCode}")
    public ResponseEntity<String> removeProductByProductCode(@PathVariable String productCode){
        return productServiceDelete.removeProductByProductCode(productCode);
    }
}
