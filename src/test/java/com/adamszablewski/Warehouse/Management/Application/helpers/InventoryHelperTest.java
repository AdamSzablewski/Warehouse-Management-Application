package com.adamszablewski.Warehouse.Management.Application.helpers;

import com.adamszablewski.Warehouse.Management.Application.Inventory.Inventory;
import com.adamszablewski.Warehouse.Management.Application.Inventory.repository.InventoryRepository;
import com.adamszablewski.Warehouse.Management.Application.Inventory.service.helpers.InventoryHelper;
import com.adamszablewski.Warehouse.Management.Application.product.Product;
import com.adamszablewski.Warehouse.Management.Application.purchaseorders.PurchaseOrder;
import com.adamszablewski.Warehouse.Management.Application.purchaseorders.PurchaseOrderItem;
import com.adamszablewski.Warehouse.Management.Application.purchaseorders.repository.PurchaseOrderRepository;
import com.adamszablewski.Warehouse.Management.Application.purchaseorders.services.purchaseOrderHelpers.PurchaseOrderHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class InventoryHelperTest {

    InventoryHelper inventoryHelper;

    @Mock
    PurchaseOrderHelper purchaseOrderHelper;

    @Mock
    InventoryRepository inventoryRepository;

    @Mock
    PurchaseOrderRepository purchaseOrderRepository;

    @BeforeEach
    void setUp(){
        inventoryHelper = new InventoryHelper(purchaseOrderRepository, purchaseOrderHelper, inventoryRepository);

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
    void removeFromInventory_shouldRemoveFromInventory(){
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
    @Test
    void removeFromInventory_shouldSendOrderForMinimalOrderder(){
        Product product = Product.builder()
                .productName("hammer")
                .unitCost(25)
                .build();

        Inventory inventory = Inventory.builder()
                .name("hammer")
                .quantity(20)
                .product(product)
                .reorderQuantity(20)
                .minimumStockLevel(15)
                .build();


        Inventory updatedInventory = inventoryHelper.removeFromInventory(inventory,8);
        verify(purchaseOrderHelper).purchase(any(PurchaseOrder.class));

        assertThat(updatedInventory.getQuantity()).isEqualTo(12);
    }

    @Test
    void automaticReorder_shouldCreatePO(){
        Product product = Product.builder()
                .productName("hammer")
                .unitCost(25)
                .build();

        Inventory inventory = Inventory.builder()
                .name("hammer")
                .quantity(20)
                .product(product)
                .reorderQuantity(20)
                .minimumStockLevel(15)
                .reorderQuantity(20)
                .build();

        PurchaseOrder poToMatch = PurchaseOrder.builder()
                .dateOfPurchase(LocalDate.now())
                .products(List.of(
                        PurchaseOrderItem.builder()
                                .name("hammer")
                                .amount(20)
                                .netPrice(25)
                                .build()
                ))
                .netPrice(500)
                .build();

        PurchaseOrder purchaseOrder = inventoryHelper.automaticReorder(inventory, 5);

        assertThat(purchaseOrder).isEqualTo(poToMatch);


    }

}
