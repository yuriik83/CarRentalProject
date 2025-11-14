package com.example.carrental.controller;

import com.example.carrental.entity.Rental;
import com.example.carrental.service.RentalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    private final RentalService service;
    public RentalController(RentalService service){ this.service = service; }

    @GetMapping
    public List<Rental> all(){ return service.findAll(); }

    @PostMapping
    public Rental create(@RequestBody Rental r){ return service.save(r); }
}
