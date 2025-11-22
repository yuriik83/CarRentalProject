package com.example.carrental.controller;

import com.example.carrental.dto.CarModelDto;
import com.example.carrental.service.CarModelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carmodels")
public class CarModelController {
    private final CarModelService service;
    public CarModelController(CarModelService service){ this.service = service; }

    @GetMapping
    public List<CarModelDto> all(){ return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<CarModelDto> get(@PathVariable Long id){ 
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CarModelDto create(@RequestBody CarModelDto dto){ return service.save(dto); }

    @PutMapping("/{id}")
    public ResponseEntity<CarModelDto> update(@PathVariable Long id, @RequestBody CarModelDto dto){
        return service.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){ 
        return service.deleteById(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
