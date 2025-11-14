package com.example.carrental.mapper;

import org.mapstruct.Mapper;
import com.example.carrental.entity.Customer;
import com.example.carrental.dto.CustomerDto;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDto toDto(Customer entity);
    Customer toEntity(CustomerDto dto);
}
