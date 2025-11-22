package com.example.carrental.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.example.carrental.entity.Rental;
import com.example.carrental.dto.RentalDto;

@Mapper(componentModel = "spring")
public interface RentalMapper {

    @Mapping(source = "car.id", target = "carId")
    @Mapping(source = "customer.id", target = "customerId")
    RentalDto toDto(Rental entity);

    @Mapping(target = "car", ignore = true)
    @Mapping(target = "customer", ignore = true)
    Rental toEntity(RentalDto dto);
}
