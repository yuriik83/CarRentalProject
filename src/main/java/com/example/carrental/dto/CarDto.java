package com.example.carrental.dto;

import lombok.Data;

@Data
public class CarDto {
    private Long id;
    private Long carModelId;
    private Long locationId;
    private String licensePlate;
    private String status;
}
