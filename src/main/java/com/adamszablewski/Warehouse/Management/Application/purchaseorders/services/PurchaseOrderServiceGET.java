package com.adamszablewski.Warehouse.Management.Application.purchaseorders.services;

import com.adamszablewski.Warehouse.Management.Application.purchaseorders.repository.PurchaseOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PurchaseOrderServiceGET {

    PurchaseOrderRepository purchaseOrderRepository;
}
