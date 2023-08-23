package com.example.apicontrolecombustivel.mapper;

import com.example.apicontrolecombustivel.dto.model.AvailabilityDto;
import com.example.apicontrolecombustivel.dto.model.UserDto;
import com.example.apicontrolecombustivel.model.jpa.*;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class AvailabilityMapper {
    public static Availability fromDtoToEntity(Long id, Sectors sector, Car car
                                                ,AvailabilityDto dto){
        return Availability.builder()
                .id(id)
                .car(car)
                .sector(sector)
                .status(dto.status())
                .build();
    }
}
