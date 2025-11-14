package com.example.carrental.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.example.carrental.entity.Car;
import com.example.carrental.dto.CarDto;

@Mapper(componentModel = "spring")
public interface CarMapper {
    @Mapping(source = "carModel.id", target = "carModelId")
    @Mapping(source = "location.id", target = "locationId")
    CarDto toDto(Car entity);

    Car toEntity(CarDto dto);
}
