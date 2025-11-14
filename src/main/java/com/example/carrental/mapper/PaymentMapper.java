package com.example.carrental.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.example.carrental.entity.Payment;
import com.example.carrental.dto.PaymentDto;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    @Mapping(source = "rental.id", target = "rentalId")
    PaymentDto toDto(Payment entity);
    Payment toEntity(PaymentDto dto);
}
