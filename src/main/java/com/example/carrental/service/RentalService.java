package com.example.carrental.service;

import com.example.carrental.dto.RentalDto;
import com.example.carrental.entity.Rental;
import com.example.carrental.mapper.RentalMapper;
import com.example.carrental.repository.RentalRepository;
import com.example.carrental.repository.CarRepository;
import com.example.carrental.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalService {
    
    private final RentalRepository repository;
    private final RentalMapper mapper;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    
    public Page<RentalDto> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toDto);
    }
    
    public List<RentalDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }
    
    public Optional<RentalDto> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }
    
    public RentalDto save(RentalDto dto) {
        Rental entity = mapper.toEntity(dto);
        if (dto.getCarId() != null) {
            entity.setCar(carRepository.findById(dto.getCarId()).orElse(null));
        }
        if (dto.getCustomerId() != null) {
            entity.setCustomer(customerRepository.findById(dto.getCustomerId()).orElse(null));
        }
        Rental saved = repository.save(entity);
        return mapper.toDto(saved);
    }
    
    public Optional<RentalDto> update(Long id, RentalDto dto) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setStartDate(dto.getStartDate());
                    existing.setEndDate(dto.getEndDate());
                    existing.setTotalCost(dto.getTotalCost());
                    if (dto.getCarId() != null) {
                        existing.setCar(carRepository.findById(dto.getCarId()).orElse(null));
                    }
                    if (dto.getCustomerId() != null) {
                        existing.setCustomer(customerRepository.findById(dto.getCustomerId()).orElse(null));
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