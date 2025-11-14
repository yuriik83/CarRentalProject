package com.example.carrental.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PaymentDto {
    private Long id;
    private Long rentalId;
    private double amount;
    private LocalDateTime paymentDate;
    private String paymentMethod;
}
