package com.adamszablewski.Warehouse.Management.Application.product.repository;

import com.adamszablewski.Warehouse.Management.Application.product.Product;
import com.adamszablewski.Warehouse.Management.Application.users.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByProductCode(String productCode);
    Optional<Product> findByProductName(String productCode);
    List<Product> findAllByCategory(String brand);
    List<Product> findAllByBrand(String brand);

    void deleteByProductName(String productName);
    void deleteByProductCode(String productName);
}
