package com.example.carrental.mapper;

import org.mapstruct.Mapper;
import com.example.carrental.entity.Location;
import com.example.carrental.dto.LocationDto;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    LocationDto toDto(Location entity);
    Location toEntity(LocationDto dto);
}
