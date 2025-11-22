package com.example.carrental.repository;

import com.example.carrental.entity.Car;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    
    @EntityGraph("Car.withAssociations")
    List<Car> findAll();
    
    @EntityGraph("Car.withAssociations")
    Optional<Car> findById(Long id);
    
    @EntityGraph("Car.withAssociations")
    @Query("SELECT c FROM Car c WHERE c.status = :status")
    List<Car> findByStatus(String status);
}
