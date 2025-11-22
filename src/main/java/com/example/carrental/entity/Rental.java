package com.example.carrental.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
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
            return Duration.between(startDate, endDate).toDays();
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rental rental = (Rental) o;
        return id != null && id.equals(rental.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
