package com.example.apicontrolecombustivel.dto.model;

import java.util.List;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDto (
        @NotBlank
        String name,
        @NotNull
        Long companyId,
        @NotNull
        Long userTypeId,
        @NotNull
        Set<Long> sectorIds
){}
