package com.example.apicontrolecombustivel.dto.model;

import com.example.apicontrolecombustivel.enums.CompanyType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

public record CompanyDto(
        @NotNull
        String razaoSocial,
        @NotNull
        String nomeFantasia,
        @NotNull
        String cnpj,
        @Enumerated(EnumType.STRING)
        CompanyType companyType
) {
}
