package com.example.apicontrolecombustivel.dto.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SectorDto(
        @NotBlank
        String name,
        @NotNull
        Long company_id
) {
}
