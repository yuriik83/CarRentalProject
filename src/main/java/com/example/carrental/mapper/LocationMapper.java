package com.example.carrental.mapper;

import com.example.carrental.dto.LocationDto;
import com.example.carrental.entity.Location;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LocationMapper {
    LocationDto toDto(Location entity);
    Location toEntity(LocationDto dto);
}