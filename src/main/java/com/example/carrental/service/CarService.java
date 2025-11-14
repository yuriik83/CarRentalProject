package com.example.carrental.service;

import com.example.carrental.entity.Car;
import com.example.carrental.entity.CarModel;
import com.example.carrental.entity.Location;
import com.example.carrental.repository.CarRepository;
import com.example.carrental.repository.CarModelRepository;
import com.example.carrental.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private final CarRepository repo;
    private final CarModelRepository modelRepo;
    private final LocationRepository locationRepo;

    public CarService(CarRepository repo, CarModelRepository modelRepo, LocationRepository locationRepo){
        this.repo = repo; this.modelRepo = modelRepo; this.locationRepo = locationRepo;
    }

    public List<Car> findAll(){ return repo.findAll(); }
    public Car findById(Long id){ return repo.findById(id).orElse(null); }

    public Car save(Car car){ // ensure associations are managed if ids present
        if (car.getCarModel() != null && car.getCarModel().getId() != null){
            CarModel m = modelRepo.findById(car.getCarModel().getId()).orElse(null);
            car.setCarModel(m);
        }
        if (car.getLocation() != null && car.getLocation().getId() != null){
            Location l = locationRepo.findById(car.getLocation().getId()).orElse(null);
            car.setLocation(l);
        }
        return repo.save(car);
    }
    public void delete(Long id){ repo.deleteById(id); }
}
