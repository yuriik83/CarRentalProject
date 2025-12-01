package com.example.carrental.service;

import com.example.carrental.dto.LocationDto;
import com.example.carrental.entity.Location;
import com.example.carrental.mapper.LocationMapper;
import com.example.carrental.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationService {
    
    private final LocationRepository repository;
    private final LocationMapper mapper;
    
    public Page<LocationDto> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toDto);
    }
    
    public List<LocationDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }
    
    public Optional<LocationDto> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }
    
    public LocationDto save(LocationDto dto) {
        Location entity = mapper.toEntity(dto);
        Location saved = repository.save(entity);
        return mapper.toDto(saved);
    }
    
    public Optional<LocationDto> update(Long id, LocationDto dto) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setCity(dto.getCity());
                    existing.setAddress(dto.getAddress());
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