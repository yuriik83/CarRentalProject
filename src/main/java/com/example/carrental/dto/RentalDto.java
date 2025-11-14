package com.example.carrental.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RentalDto {
    private Long id;
    private Long carId;
    private Long customerId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double totalCost;
}
