package com.example.carrental.controller;

import com.example.carrental.dto.CustomerDto;
import com.example.carrental.service.CustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
@Tag(name = "Customers", description = "Управление клиентами")
public class CustomerController {
    private final CustomerService service;
    
    public CustomerController(CustomerService service) { 
        this.service = service; 
    }

    @GetMapping
    public Page<CustomerDto> all(Pageable pageable){ return service.findAll(pageable); }
    
    @GetMapping("/all")
    public List<CustomerDto> allWithoutPagination(){ return service.findAll(); }

    @GetMapping("/{id}")
    public CustomerDto get(@PathVariable Long id){ 
        return service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto create(@Valid @RequestBody CustomerDto dto) { 
        return service.save(dto);
    }

    @PutMapping("/{id}")
    public CustomerDto update(@PathVariable Long id, @Valid @RequestBody CustomerDto dto){
        return service.update(id, dto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){ 
        if (!service.deleteById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
