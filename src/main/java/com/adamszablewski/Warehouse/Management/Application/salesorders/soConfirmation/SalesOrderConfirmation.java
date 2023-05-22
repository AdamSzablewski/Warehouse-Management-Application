package com.adamszablewski.Warehouse.Management.Application.salesorders.soConfirmation;

import com.adamszablewski.Warehouse.Management.Application.purchaseorders.PurchaseOrder;
import com.adamszablewski.Warehouse.Management.Application.salesorders.SalesOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class SalesOrderConfirmation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private String deliveryAddress;
    private String contactEmail;
    private String contactPhone;
    private String contactPerson;
    private String company;


    @OneToOne
    private SalesOrder purchaseOrder;
    private String buyerTrackingId;
    private LocalDate deliveryDate;

}
