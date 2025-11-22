package com.example.carrental.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@NamedEntityGraph(
    name = "Car.withAssociations",
    attributeNodes = {
        @NamedAttributeNode("carModel"),
        @NamedAttributeNode("location")
    }
)
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private CarModel carModel;

    @ManyToOne
    private Location location;

    private String licensePlate;
    private String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;

        return id != null && id.equals(car.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}