//package com.adamszablewski.Warehouse.Management.Application.product.service;
//
//import com.adamszablewski.Warehouse.Management.Application.product.Product;
//import com.adamszablewski.Warehouse.Management.Application.product.repository.ProductRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//@Service
//@AllArgsConstructor
//public class ProductServicePut {
//
//    ProductRepository productRepository;
//    public ResponseEntity<String> updateProduct(Product productInfo, int id) {
//        Optional<Product> optionalProduct = productRepository.findById(id);
//        if (optionalProduct.isEmpty()){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with such id does not exist");
//        }
//
//        Product product = optionalProduct.get();
//
//        product.setProductCode(productInfo.getProductCode());
//        product.setProductName(productInfo.getProductName());
//        product.setDimensions(product.getDimensions());
//        product.setBrand(product.getBrand());
//        product.setCategory(product.getCategory());
//        product.setDescription(product.getDescription());
//        product.setFragile(product.isFragile());
//        product.setWeight(product.getWeight());
//        product.setLeadTime(product.getLeadTime());
//        product.setDimensions(product.getDimensions());
//        product.setStorageTemperature(product.getStorageTemperature());
//        product.setUnitOfMeasure(product.getUnitOfMeasure());
//
//        productRepository.save(product);
//
//        return ResponseEntity.ok("Product updated successfully");
//    }
//}
