package com.example.carrental.service;

import com.example.carrental.entity.CarModel;
import com.example.carrental.repository.CarModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarModelService {
    private final CarModelRepository repo;
    public CarModelService(CarModelRepository repo) { this.repo = repo; }
    public List<CarModel> findAll(){ return repo.findAll(); }
    public CarModel findById(Long id){ return repo.findById(id).orElse(null); }
    public CarModel save(CarModel m){ return repo.save(m); }
    public void delete(Long id){ repo.deleteById(id); }
}
