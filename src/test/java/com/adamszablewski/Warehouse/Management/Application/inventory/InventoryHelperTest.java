package com.adamszablewski.Warehouse.Management.Application.inventory;

import com.adamszablewski.Warehouse.Management.Application.Inventory.Inventory;
import com.adamszablewski.Warehouse.Management.Application.Inventory.service.helpers.InventoryHelper;
import com.adamszablewski.Warehouse.Management.Application.product.Product;
import com.adamszablewski.Warehouse.Management.Application.purchaseorders.PurchaseOrder;
import com.adamszablewski.Warehouse.Management.Application.purchaseorders.repository.PurchaseOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class InventoryHelperTest {

    InventoryHelper inventoryHelper;
    @Mock
    PurchaseOrderRepository purchaseOrderRepository;

    @BeforeEach
    void setUp(){
        inventoryHelper = new InventoryHelper(purchaseOrderRepository);
    }

    @Test
    void addToInventory_shouldAdd(){
        Inventory inventory = Inventory.builder()
                .name("hammer")
                .quantity(20)
                .build();
        int updatedLevel = inventoryHelper.addToInventory(inventory, 5);

        assertThat(updatedLevel).isEqualTo(inventory.getQuantity());
    }

    @Test
    void automaticReorder_should_send_order_for_minimal_order(){
        Product product = Product.builder()
                .productName("hammer")
                .unitCost(25)
                .build();

        Inventory inventory = Inventory.builder()
                .name("hammer")
                .quantity(20)
                .product(product)
                .reorderQuantity(20)
                .minimumStockLevel(10)
                .build();


        Inventory updatedInventory = inventoryHelper.removeFromInventory(inventory,8);

        assertThat(updatedInventory.getQuantity()).isEqualTo(12);


    }

}
