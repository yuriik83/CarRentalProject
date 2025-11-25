package com.example.carrental.controller;

import com.example.carrental.dto.ApiInfoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@Tag(name = "System", description = "Системная информация")
public class ApiController {

    @GetMapping("/info")
    @Operation(summary = "Получить информацию о API")
    public ResponseEntity<ApiInfoResponse> getApiInfo() {
        ApiInfoResponse info = new ApiInfoResponse(
            "Car Rental System",
            "1.0.0",
            "REST API для системы аренды автомобилей",
            Map.of(
                "carmodels", "/api/carmodels - Управление моделями автомобилей",
                "cars", "/api/cars - Управление автомобилями", 
                "customers", "/api/customers - Управление клиентами",
                "locations", "/api/locations - Управление локациями",
                "rentals", "/api/rentals - Управление арендой",
                "payments", "/api/payments - Управление платежами"
            ),
            Map.of(
                "spring_boot", "3.2.4",
                "jpa", "Hibernate 6.4.4",
                "mapstruct", "1.5.5",
                "database", "PostgreSQL",
                "dto_mapping", "Автоматический маппинг с MapStruct",
                "crud_operations", "Полный CRUD для всех сущностей"
            )
        );
        return ResponseEntity.ok(info);
    }


}