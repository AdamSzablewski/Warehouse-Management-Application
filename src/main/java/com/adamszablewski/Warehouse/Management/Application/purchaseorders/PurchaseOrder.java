package com.adamszablewski.Warehouse.Management.Application.purchaseorders;

import com.adamszablewski.Warehouse.Management.Application.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@Entity
@NoArgsConstructor
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int POid;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PurchaseOrderItem> products;

    boolean delivered;

    private double netPrice;

    LocalDate dateOfPurchase;

    LocalDate dateOfDelivery;





}
