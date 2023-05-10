package com.adamszablewski.Warehouse.Management.Application.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Entity
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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
