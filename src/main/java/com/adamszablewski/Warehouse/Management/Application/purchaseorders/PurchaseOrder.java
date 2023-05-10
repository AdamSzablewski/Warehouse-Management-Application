package com.adamszablewski.Warehouse.Management.Application.purchaseorders;

import com.adamszablewski.Warehouse.Management.Application.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@Entity
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @OneToMany
    private Product product;

    private double netPrice;

    private int amount;

    LocalDate dateOfPurchase;





}
