package com.adamszablewski.Warehouse.Management.Application.salesorders;

import com.adamszablewski.Warehouse.Management.Application.users.UserInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    private boolean orderRecieved;
    private boolean transactionClosed;

    @NotEmpty
    private String username;
    private String company;
    private String buyerTrackingId;

    @NotEmpty
    private String deliveryAdress;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL)
    private List<SalesOrderItem> items;

    private LocalDate dateReceived;
    private double totalAmount;

    @NotEmpty
    private String contactPerson;
    @NotEmpty
    private String contactNumber;
    @NotEmpty
    private String contactEmail;
}
