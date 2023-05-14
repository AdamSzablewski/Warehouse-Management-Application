package com.adamszablewski.Warehouse.Management.Application.product.service;

import com.adamszablewski.Warehouse.Management.Application.product.Product;
import com.adamszablewski.Warehouse.Management.Application.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductService {

    ProductRepository productRepository;

    public List<Product> retrieveAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> retrieveProductByProductCode(String product_code) {
        return productRepository.findByProductCode(product_code);
    }

    public Optional<Product> retrieveProductByProductName(String productName) {
        return productRepository.findByProductName(productName);
    }

    public List<Product> retrieveProductByCategory(String category) {
        return productRepository.findAllByCategory(category);
    }

    public List<Product> retrieveProductByBrand(String brand) {
        return productRepository.findAllByBrand(brand);
    }

    public ResponseEntity<String> createProduct(Product product) {
        productRepository.save(product);
        return ResponseEntity.ok("Product created");
    }

    public ResponseEntity<String> updateProduct(Product productInfo, int id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with such id does not exist");
        }

        Product product = optionalProduct.get();

        product.setProductCode(productInfo.getProductCode());
        product.setProductName(productInfo.getProductName());
        product.setDimensions(product.getDimensions());
        product.setBrand(product.getBrand());
        product.setCategory(product.getCategory());
        product.setDescription(product.getDescription());
        product.setFragile(product.isFragile());
        product.setWeight(product.getWeight());
        product.setLeadTime(product.getLeadTime());
        product.setDimensions(product.getDimensions());
        product.setStorageTemperature(product.getStorageTemperature());
        product.setUnitOfMeasure(product.getUnitOfMeasure());

        productRepository.save(product);

        return ResponseEntity.ok("Product updated successfully");
    }


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
