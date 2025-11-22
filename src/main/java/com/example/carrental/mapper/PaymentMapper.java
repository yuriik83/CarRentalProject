package com.example.carrental.mapper;

import com.example.carrental.dto.PaymentDto;
import com.example.carrental.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentMapper {
    
    @Mapping(source = "rental.id", target = "rentalId")
    PaymentDto toDto(Payment entity);
    
    @Mapping(source = "rentalId", target = "rental.id")
    Payment toEntity(PaymentDto dto);
}