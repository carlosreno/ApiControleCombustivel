package com.example.apicontrolecombustivel.dto.projectionsDto;

import com.example.apicontrolecombustivel.enums.CompanyType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CompanyResponse(
        Long id,
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
