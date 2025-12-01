package com.example.carrental.controller;

import com.example.carrental.dto.CarDto;
import com.example.carrental.service.CarService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@CrossOrigin(origins = "*")
@Tag(name = "Cars", description = "Управление автомобилями")
public class CarController {
    private final CarService service;

    public CarController(CarService service) {
        this.service = service;
    }

    @GetMapping
    public Page<CarDto> findAll(Pageable pageable){ return service.findAll(pageable); }
    
    @GetMapping("/all")
    public List<CarDto> findAllWithoutPagination(){ return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> findById(@PathVariable Long id){ 
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CarDto> create(@Valid @RequestBody CarDto dto) { 
        CarDto created = service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDto> update(@PathVariable Long id, @Valid @RequestBody CarDto dto){
        return service.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){ 
        return service.deleteById(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
