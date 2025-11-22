package com.example.carrental.service;

import com.example.carrental.dto.CarModelDto;
import com.example.carrental.entity.CarModel;
import com.example.carrental.mapper.CarModelMapper;
import com.example.carrental.repository.CarModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarModelService {
    
    private final CarModelRepository repository;
    private final CarModelMapper mapper;
    
    public List<CarModelDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }
    
    public Optional<CarModelDto> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }
    
    public CarModelDto save(CarModelDto dto) {
        CarModel entity = mapper.toEntity(dto);
        CarModel saved = repository.save(entity);
        return mapper.toDto(saved);
    }
    
    public Optional<CarModelDto> update(Long id, CarModelDto dto) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setBrand(dto.getBrand());
                    existing.setModel(dto.getModel());
                    existing.setFuelType(dto.getFuelType());
                    existing.setDailyRate(dto.getDailyRate());
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