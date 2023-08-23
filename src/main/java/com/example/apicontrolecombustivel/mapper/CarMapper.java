package com.example.apicontrolecombustivel.mapper;

import com.example.apicontrolecombustivel.dto.model.CarDto;
import com.example.apicontrolecombustivel.model.jpa.*;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class CarMapper {
    public static Car fromDtoToEntity(Long id, CarDto dto,Company company
                                    ,TypeVehicles typeVehicles){
        return Car.builder()
                .id(id)
                .placa(dto.placa())
                .renavam(dto.renavam())
                .typeVehicles(typeVehicles)
                .brand(dto.brand())
                .color(dto.color())
                .mileage(dto.mileage())
                .fabricationDate(dto.fabricationDate())
                .comments(dto.comments())
                .company(company)
                .status(dto.status())
                .build();
    }
}
