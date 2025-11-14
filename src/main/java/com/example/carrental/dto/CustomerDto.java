package com.example.carrental.dto;

import com.example.carrental.entity.Role;
import lombok.Data;

@Data
public class CustomerDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Role role;
}
