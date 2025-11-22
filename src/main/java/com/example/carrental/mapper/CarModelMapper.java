package com.example.carrental.mapper;

import com.example.carrental.dto.CarModelDto;
import com.example.carrental.entity.CarModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CarModelMapper {
    CarModelDto toDto(CarModel entity);
    CarModel toEntity(CarModelDto dto);
}