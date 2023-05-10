package com.adamszablewski.Warehouse.Management.Application.Inventory;

import com.adamszablewski.Warehouse.Management.Application.product.Product;
import com.adamszablewski.Warehouse.Management.Application.warehouse.Warehouse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Inventory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Warehouse warehouse;

    private LocalDate dateAdded;

    private int quantity;

    private int minimumStockLevel;

    private int maximumStockLevel;

    private int reorderQuantity;

    private int minimumOrderQuantity;

    private int maximumOrderQuantity;

    private String storageLocation;


}
