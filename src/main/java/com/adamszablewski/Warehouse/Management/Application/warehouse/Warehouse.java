package com.adamszablewski.Warehouse.Management.Application.warehouse;

import com.adamszablewski.Warehouse.Management.Application.Inventory.Inventory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private String city;
    private String province;
    private String zipCode;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Inventory> inventory;



}
