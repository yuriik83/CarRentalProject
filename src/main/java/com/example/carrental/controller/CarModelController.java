package com.example.carrental.controller;

import com.example.carrental.dto.CarModelDto;
import com.example.carrental.service.CarModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carmodels")
@CrossOrigin(origins = "*")
@Tag(name = "Car Models", description = "Управление моделями автомобилей")
public class CarModelController {
    private final CarModelService service;
    
    public CarModelController(CarModelService service) { 
        this.service = service; 
    }

    @GetMapping
    public Page<CarModelDto> all(Pageable pageable){ return service.findAll(pageable); }
    
    @GetMapping("/all")
    public List<CarModelDto> allWithoutPagination(){ return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<CarModelDto> get(@PathVariable Long id){ 
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CarModelDto> create(@Valid @RequestBody CarModelDto dto) { 
        CarModelDto created = service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarModelDto> update(@PathVariable Long id, @Valid @RequestBody CarModelDto dto){
        return service.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){ 
        return service.deleteById(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
