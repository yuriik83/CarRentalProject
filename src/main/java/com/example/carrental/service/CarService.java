package com.example.carrental.service;

import com.example.carrental.dto.CarDto;
import com.example.carrental.entity.Car;
import com.example.carrental.mapper.CarMapper;
import com.example.carrental.repository.CarRepository;
import com.example.carrental.repository.CarModelRepository;
import com.example.carrental.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {
    
    private final CarRepository repository;
    private final CarMapper mapper;
    private final CarModelRepository carModelRepository;
    private final LocationRepository locationRepository;
    
    public Page<CarDto> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toDto);
    }
    
    public List<CarDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }
    
    public Optional<CarDto> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }
    
    public CarDto save(CarDto dto) {
        Car entity = mapper.toEntity(dto);
        if (dto.getCarModelId() != null) {
            entity.setCarModel(carModelRepository.findById(dto.getCarModelId()).orElse(null));
        }
        if (dto.getLocationId() != null) {
            entity.setLocation(locationRepository.findById(dto.getLocationId()).orElse(null));
        }
        Car saved = repository.save(entity);
        return mapper.toDto(saved);
    }
    
    public Optional<CarDto> update(Long id, CarDto dto) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setLicensePlate(dto.getLicensePlate());
                    existing.setStatus(dto.getStatus());
                    if (dto.getCarModelId() != null) {
                        existing.setCarModel(carModelRepository.findById(dto.getCarModelId()).orElse(null));
                    }
                    if (dto.getLocationId() != null) {
                        existing.setLocation(locationRepository.findById(dto.getLocationId()).orElse(null));
                    }
                    return mapper.toDto(repository.save(existing));
                });
    }
    
    public boolean deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}