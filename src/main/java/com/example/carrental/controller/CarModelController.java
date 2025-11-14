package com.example.carrental.controller;

import com.example.carrental.entity.CarModel;
import com.example.carrental.service.CarModelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carmodels")
public class CarModelController {
    private final CarModelService service;
    public CarModelController(CarModelService service){ this.service = service; }

    @GetMapping
    public List<CarModel> all(){ return service.findAll(); }

    @GetMapping("/{id}")
    public CarModel get(@PathVariable Long id){ return service.findById(id); }

    @PostMapping
    public CarModel create(@RequestBody CarModel m){ return service.save(m); }

    @PutMapping("/{id}")
    public CarModel update(@PathVariable Long id, @RequestBody CarModel m){
        m.setId(id); return service.save(m);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){ service.delete(id); }
}
