package com.example.carrental.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ApiController {

    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> getApiInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("application", "Car Rental System");
        info.put("version", "1.0.0");
        info.put("description", "REST API для системы аренды автомобилей");
        info.put("endpoints", Map.of(
            "carmodels", "/api/carmodels - Управление моделями автомобилей",
            "cars", "/api/cars - Управление автомобилями", 
            "customers", "/api/customers - Управление клиентами",
            "locations", "/api/locations - Управление локациями",
            "rentals", "/api/rentals - Управление арендой",
            "payments", "/api/payments - Управление платежами"
        ));
        info.put("features", Map.of(
            "spring_boot", "3.2.4",
            "jpa", "Hibernate 6.4.4",
            "mapstruct", "1.5.5",
            "database", "PostgreSQL",
            "dto_mapping", "Автоматический маппинг с MapStruct",
            "crud_operations", "Полный CRUD для всех сущностей"
        ));
        return ResponseEntity.ok(info);
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        Map<String, String> health = new HashMap<>();
        health.put("status", "UP");
        health.put("message", "Приложение работает корректно");
        health.put("timestamp", java.time.LocalDateTime.now().toString());
        return ResponseEntity.ok(health);
    }
}