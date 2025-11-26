package com.example.carrental.controller;

import com.example.carrental.dto.RentalDto;
import com.example.carrental.service.RentalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
@CrossOrigin(origins = "*")
@Tag(name = "Rentals", description = "Управление арендой")
public class RentalController {
    private final RentalService service;
    
    public RentalController(RentalService service) { 
        this.service = service; 
    }

    @GetMapping
    public List<RentalDto> all() { 
        return service.findAll(); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalDto> get(@PathVariable Long id) { 
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RentalDto> create(@Valid @RequestBody RentalDto dto) { 
        RentalDto created = service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RentalDto> update(@PathVariable Long id, @Valid @RequestBody RentalDto dto){
        return service.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){ 
        return service.deleteById(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
