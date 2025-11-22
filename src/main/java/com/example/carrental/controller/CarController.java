package com.example.carrental.controller;

import com.example.carrental.dto.CarDto;
import com.example.carrental.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    private final CarService service;

    public CarController(CarService service) {
        this.service = service;
    }

    @GetMapping
    public List<CarDto> findAll(){ return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> findById(@PathVariable Long id){ 
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CarDto create(@RequestBody CarDto dto){ return service.save(dto); }

    @PutMapping("/{id}")
    public ResponseEntity<CarDto> update(@PathVariable Long id, @RequestBody CarDto dto){
        return service.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){ 
        return service.deleteById(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
