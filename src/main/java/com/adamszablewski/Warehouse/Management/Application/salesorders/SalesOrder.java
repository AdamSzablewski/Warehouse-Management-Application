package com.adamszablewski.Warehouse.Management.Application.salesorders;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Builder
@NoArgsConstructor
public class SalesOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean inDelivery;
    private boolean transactionClosed;
    private String company;

    @OneToMany(cascade = CascadeType.ALL)
    private List<SalesOrderItem> items;
    private LocalDate dateReceived;
    private double totalAmount;
    private String contactPerson;
    private String contactNumber;
}
