package com.example.carrental.mapper;

import com.example.carrental.dto.CarDto;
import com.example.carrental.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CarMapper {
    
    @Mapping(source = "carModel.id", target = "carModelId")
    @Mapping(source = "location.id", target = "locationId")
    CarDto toDto(Car entity);
    
    @Mapping(source = "carModelId", target = "carModel.id")
    @Mapping(source = "locationId", target = "location.id")
    Car toEntity(CarDto dto);
}