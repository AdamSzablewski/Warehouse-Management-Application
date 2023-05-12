package com.adamszablewski.Warehouse.Management.Application.product;

import com.adamszablewski.Warehouse.Management.Application.Inventory.Inventory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private Inventory inventory;

    private String productName;

    private String productCode;

    private String description;

    private String category;

    private String brand;

    private String unitOfMeasure;

    private double weight;

    private boolean isFragile;

    private double dimensions;

    private double storageTemperature;

    private double unitCost;

    private double leadTime;












}
