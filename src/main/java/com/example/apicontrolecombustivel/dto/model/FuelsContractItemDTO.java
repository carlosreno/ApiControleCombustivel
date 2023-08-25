package com.example.apicontrolecombustivel.dto.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record FuelsContractItemDTO(
        @NotNull
        Long fuelId,
        @NotNull
        Integer amount,
        @NotNull
        BigDecimal pricePerUnit,
        BigDecimal totalCost
)
{}
