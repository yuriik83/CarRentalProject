package com.example.carrental.mapper;

import com.example.carrental.dto.CustomerDto;
import com.example.carrental.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {
    CustomerDto toDto(Customer entity);
    Customer toEntity(CustomerDto dto);
}