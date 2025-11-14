package com.example.carrental.repository;

import com.example.carrental.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
public interface LocationRepository extends JpaRepository<Location, Long> {
}
