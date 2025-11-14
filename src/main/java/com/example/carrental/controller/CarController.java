package com.example.carrental.controller;

import com.example.carrental.entity.Car;
import com.example.carrental.entity.Customer;
import com.example.carrental.entity.Role;
import com.example.carrental.service.CarService;
import com.example.carrental.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    private final CarService service;
    private final CustomerService customerService;
    private List<String> cars = new ArrayList<>();

    public CarController(CarService service, CustomerService customerService) {
        this.service = service;
        this.customerService = customerService;
        cars.add("Toyota Corolla");
        cars.add("Tesla Model 3");
    }

    @GetMapping
    public List<Car> all(){ return service.findAll(); }

    @GetMapping("/{id}")
    public Car get(@PathVariable Long id){ return service.findById(id); }

    @PostMapping
    public String addCar(@RequestParam Long customerId, @RequestBody String carName) {
        Customer c = customerService.findById(customerId);
        if (c == null || c.getRole() != Role.ADMIN)
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only ADMIN can add cars");
        cars.add(carName);
        return "Added: " + carName;
    }
    @DeleteMapping("/{carName}")
    public String deleteCar(@RequestParam Long customerId, @PathVariable String carName) {
        Customer c = customerService.findById(customerId);
        if (c == null || c.getRole() != Role.ADMIN)
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only ADMIN can delete cars");
        cars.remove(carName);
        return "Deleted: " + carName;
    }

    @PostMapping
    public Car create(@RequestBody Car c){ return service.save(c); }

    @PutMapping("/{id}")
    public Car update(@PathVariable Long id, @RequestBody Car c){
        c.setId(id); return service.save(c);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){ service.delete(id); }
}
