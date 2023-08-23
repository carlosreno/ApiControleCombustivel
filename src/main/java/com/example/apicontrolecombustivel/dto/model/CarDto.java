package com.example.apicontrolecombustivel.dto.model;

import com.example.apicontrolecombustivel.enums.OnOrOf;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;
import java.util.List;

public record CarDto(
        @NotBlank
        String placa,
        String renavam,
        @NotBlank
        Long typeVehicles,
        String brand,
        Integer year,
        String color,
        List<Long> fuelIds,
        Double mileage,
        Date fabricationDate,
        String comments,
        Long companyId,
        @Enumerated(EnumType.STRING)
        OnOrOf status
) {
}
