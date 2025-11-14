package com.example.carrental.mapper;

import org.mapstruct.Mapper;
import com.example.carrental.entity.CarModel;
import com.example.carrental.dto.CarModelDto;

@Mapper(componentModel = "spring")
public interface CarModelMapper {
    CarModelDto toDto(CarModel entity);
    CarModel toEntity(CarModelDto dto);
}
