package com.adamszablewski.Warehouse.Management.Application.purchaseorders.repository;

import com.adamszablewski.Warehouse.Management.Application.purchaseorders.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {
}
