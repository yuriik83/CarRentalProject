package com.example.carrental.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Car car;

    @ManyToOne
    private Customer customer;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double totalCost;

    public long getDurationDays() {
        if (startDate != null && endDate != null) {
            return java.time.Duration.between(startDate, endDate).toDays();
        }
        return 0;
    }
}
