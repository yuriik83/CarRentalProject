package com.example.carrental.mapper;

import org.springframework.stereotype.Component;
import com.example.carrental.entity.Car;
import com.example.carrental.dto.CarDto;

@Component
public class CarMapper {
    
    public CarDto toDto(Car entity) {
        if (entity == null) return null;
        
        CarDto dto = new CarDto();
        dto.setId(entity.getId());
        dto.setCarModelId(entity.getCarModel() != null ? entity.getCarModel().getId() : null);
        dto.setLocationId(entity.getLocation() != null ? entity.getLocation().getId() : null);
        dto.setLicensePlate(entity.getLicensePlate());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public Car toEntity(CarDto dto) {
        if (dto == null) return null;
        
        Car entity = new Car();
        entity.setLicensePlate(dto.getLicensePlate());
        entity.setStatus(dto.getStatus());
        return entity;
    }
}
