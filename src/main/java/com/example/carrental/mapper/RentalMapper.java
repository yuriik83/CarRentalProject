package com.example.carrental.mapper;

import com.example.carrental.dto.RentalDto;
import com.example.carrental.entity.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RentalMapper {
    
    @Mapping(source = "car.id", target = "carId")
    @Mapping(source = "customer.id", target = "customerId")
    RentalDto toDto(Rental entity);
    
    @Mapping(source = "carId", target = "car.id")
    @Mapping(source = "customerId", target = "customer.id")
    Rental toEntity(RentalDto dto);
}