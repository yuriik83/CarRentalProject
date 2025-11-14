package com.example.carrental.controller;

import com.example.carrental.entity.Location;
import com.example.carrental.service.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {
    private final LocationService service;
    public LocationController(LocationService service){ this.service = service; }

    @GetMapping
    public List<Location> all(){ return service.findAll(); }

    @PostMapping
    public Location create(@RequestBody Location l){ return service.save(l); }
}
