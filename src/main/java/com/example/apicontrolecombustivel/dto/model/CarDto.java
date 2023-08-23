package com.example.apicontrolecombustivel.dto.model;

import com.example.apicontrolecombustivel.enums.OnOrOf;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public record CarDto(
        @NotBlank
        String placa,
        @NotBlank
        String renavam,
        @NotBlank
        Long typeVehicles,
        String brand,
        String color,
        Double mileage,
        @NotBlank
        LocalDate fabricationDate,
        String comments,
        Long companyId,
        @Enumerated(EnumType.STRING)
        OnOrOf status
) {
}
