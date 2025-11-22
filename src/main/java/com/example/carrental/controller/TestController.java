package com.example.carrental.controller;

import com.example.carrental.dto.CarModelDto;
import com.example.carrental.service.CarModelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestController {
    
    private final CarModelService carModelService;
    
    public TestController(CarModelService carModelService) {
        this.carModelService = carModelService;
    }
    
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Spring Boot приложение работает! MapStruct и сервисы настроены.");
    }
    
    @PostMapping("/carmodel")
    public ResponseEntity<CarModelDto> createTestCarModel() {
        CarModelDto dto = new CarModelDto();
        dto.setBrand("Toyota");
        dto.setModel("Camry");
        dto.setFuelType("Бензин");
        dto.setDailyRate(2500.0);
        
        CarModelDto saved = carModelService.save(dto);
        return ResponseEntity.ok(saved);
    }
    
    @GetMapping("/carmodels")
    public ResponseEntity<List<CarModelDto>> getAllCarModels() {
        return ResponseEntity.ok(carModelService.findAll());
    }
}