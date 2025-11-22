package com.example.carrental.service;

import com.example.carrental.entity.Car;
import com.example.carrental.entity.CarModel;
import com.example.carrental.entity.Location;
import com.example.carrental.repository.CarRepository;
import com.example.carrental.repository.CarModelRepository;
import com.example.carrental.repository.LocationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CarService {
    private final CarRepository repo;
    private final CarModelRepository modelRepo;
    private final LocationRepository locationRepo;

    public CarService(CarRepository repo, CarModelRepository modelRepo, LocationRepository locationRepo){
        this.repo = repo; this.modelRepo = modelRepo; this.locationRepo = locationRepo;
    }

    public List<Car> findAll(){ return repo.findAll(); }
    public Car findById(Long id){ return repo.findById(id).orElse(null); }

    @Transactional
    public Car save(Car car){
        if (car.getCarModel() != null && car.getCarModel().getId() != null){
            car.setCarModel(modelRepo.getReferenceById(car.getCarModel().getId()));
        }
        if (car.getLocation() != null && car.getLocation().getId() != null){
            car.setLocation(locationRepo.getReferenceById(car.getLocation().getId()));
        }
        return repo.save(car);
    }
    
    @Transactional
    public void delete(Long id){ repo.deleteById(id); }
}
