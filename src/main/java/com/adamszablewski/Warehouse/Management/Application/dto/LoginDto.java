package com.adamszablewski.Warehouse.Management.Application.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String email;
    private String password;
    private String phoneNumber;
}
