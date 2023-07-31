package com.example.apicontrolecombustivel.dto.model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

public record CompanyDto(
        @NotNull
        String name,
        @NotNull
        String cnpj,
        String address,
        String phone,
        String email
) {
}
