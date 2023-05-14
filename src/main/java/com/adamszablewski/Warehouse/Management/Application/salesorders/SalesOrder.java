package com.adamszablewski.Warehouse.Management.Application.salesorders;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Builder
public class SalesOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean inDelivery;
    private String company;

    @OneToMany
    private List<SalesOrderItem> items;
    private LocalDate dateReceived;
    private double totalAmount;
    private String supplierName;
    private String supplierAddress;
    private String contactPerson;
    private String contactNumber;
}
