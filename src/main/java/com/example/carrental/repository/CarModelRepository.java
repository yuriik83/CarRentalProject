package com.example.carrental.repository;

import com.example.carrental.entity.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CarModelRepository extends JpaRepository<CarModel, Long> {
}
