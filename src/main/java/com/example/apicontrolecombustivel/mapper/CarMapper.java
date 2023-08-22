package com.example.apicontrolecombustivel.mapper;

import com.example.apicontrolecombustivel.dto.model.UserDto;
import com.example.apicontrolecombustivel.model.jpa.*;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class CarMapper {
    public static Car fromDtoToEntity(Long id,Company company, Availability availabilityActive){
        return Car.builder()
                .id(id)
                .activeAvailability()
                .build();
    }
}
