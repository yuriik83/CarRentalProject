package com.example.carrental.controller;

import com.example.carrental.dto.LocationDto;
import com.example.carrental.service.LocationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
@CrossOrigin(origins = "*")
@Tag(name = "Locations", description = "Управление локациями")
public class LocationController {
    private final LocationService service;
    
    public LocationController(LocationService service) { 
        this.service = service; 
    }

    @GetMapping
    public Page<LocationDto> all(Pageable pageable) { 
        return service.findAll(pageable); 
    }
    
    @GetMapping("/all")
    public List<LocationDto> allWithoutPagination() { 
        return service.findAll(); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationDto> get(@PathVariable Long id) { 
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LocationDto> create(@Valid @RequestBody LocationDto dto) { 
        LocationDto created = service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocationDto> update(@PathVariable Long id, @Valid @RequestBody LocationDto dto){
        return service.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){ 
        return service.deleteById(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
