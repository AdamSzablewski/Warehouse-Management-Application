package com.adamszablewski.Warehouse.Management.Application.messages;

import com.adamszablewski.Warehouse.Management.Application.users.UserInfo;
import com.adamszablewski.Warehouse.Management.Application.vendor.Vendor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private Vendor vendor;
    private String sender;
    private String replier;
    @OneToMany
    private List<Message> messages;

}
