//package com.adamszablewski.Warehouse.Management.Application.purchaseorders.services;
//
//import com.adamszablewski.Warehouse.Management.Application.Inventory.Inventory;
//import com.adamszablewski.Warehouse.Management.Application.Inventory.repository.InventoryRepository;
//import com.adamszablewski.Warehouse.Management.Application.Inventory.service.helpers.InventoryHelper;
//import com.adamszablewski.Warehouse.Management.Application.product.Product;
//import com.adamszablewski.Warehouse.Management.Application.purchaseorders.PurchaseOrder;
//import com.adamszablewski.Warehouse.Management.Application.purchaseorders.repository.PurchaseOrderRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.Optional;
//
//@Service
//@AllArgsConstructor
//public class PurchaseOrderServicePUT {
//
//    PurchaseOrderRepository purchaseOrderRepository;
//    InventoryHelper inventoryHelper;
//
//    InventoryRepository inventoryRepository;
//
//    public ResponseEntity<String> markAsDelivered(int id) {
//        Optional<PurchaseOrder> optionalPurchaseOrder = purchaseOrderRepository.findById(id);
//        if (optionalPurchaseOrder.isEmpty()){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such purchase order exist");
//        }
//
//        PurchaseOrder purchaseOrder = optionalPurchaseOrder.get();
//
//
//        for (Product p : purchaseOrder.getProducts()){
//            Optional<Inventory> optionalInventory = inventoryRepository.findByName(p.getProductName());
//            if(optionalInventory.isEmpty()){
//                //add error
//            }else {
//                Inventory inventory = optionalInventory.get();
//                inventoryHelper.addToInventory(inventory, purchaseOrder.getAmount());
//                inventoryRepository.save(inventory);
//            }
//
//        }
//
//        purchaseOrder.setDelivered(true);
//        purchaseOrder.setDateOfDelivery(LocalDate.now());
//
//        purchaseOrderRepository.save(purchaseOrder);
//
//
//        return ResponseEntity.ok("Products successfully added to inventory");
//
//    }
//}
