package com.example.carrental.controller;

import com.example.carrental.entity.Car;
import com.example.carrental.service.CarService;
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
    public List<Car> findAll(){ return service.findAll(); }

    @GetMapping("/{id}")
    public Car findById(@PathVariable Long id){ return service.findById(id); }

    @PostMapping
    public Car create(@RequestBody Car car){ return service.save(car); }

    @PutMapping("/{id}")
    public Car update(@PathVariable Long id, @RequestBody Car car){
        car.setId(id);
        return service.save(car);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){ service.delete(id); }
}
