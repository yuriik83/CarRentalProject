package com.example.carrental.dto;

import lombok.Data;

@Data
public class CarModelDto {
    private Long id;
    private String brand;
    private String model;
    private String fuelType;
    private double dailyRate;
}
