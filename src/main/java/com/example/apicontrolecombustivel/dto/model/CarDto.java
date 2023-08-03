package com.example.apicontrolecombustivel.dto.model;

import com.example.apicontrolecombustivel.enums.OnOrOf;
import com.example.apicontrolecombustivel.model.jpa.Availability;
import com.example.apicontrolecombustivel.model.jpa.Company;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record CarDto(
        @NotBlank
        String placa,
        String renavam,
        @NotBlank
        String type,
        String marca,
        String brand,
        Integer year,
        String color,
        String fuel,
        Double mileage,
        Date fabricationDate,
        String comments,
        Company company,
        Availability activeAvailability,
        @Enumerated(EnumType.STRING)
        OnOrOf status
) {
}
