//package com.adamszablewski.Warehouse.Management.Application.purchaseorders.services;
//
//import com.adamszablewski.Warehouse.Management.Application.purchaseorders.PurchaseOrder;
//import com.adamszablewski.Warehouse.Management.Application.purchaseorders.repository.PurchaseOrderRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class PurchaseOrderServicePOST {
//
//    PurchaseOrderRepository purchaseOrderRepository;
//
//    public ResponseEntity<String> purchase(PurchaseOrder purchaseOrder) {
//        purchaseOrderRepository.save(purchaseOrder);
//        //sending it out
//        return ResponseEntity.ok("Purchase order sent succesfully to the vendor");
//    }
//}
