package com.example.apicontrolecombustivel.dto.projectionsDto;

import com.example.apicontrolecombustivel.dto.model.CompanyDto;
import com.example.apicontrolecombustivel.enums.ContractStatus;
import lombok.Builder;

import java.time.LocalDate;
@Builder
public record ContractResponse(
    Long id,
    String numberContract,
    String object,
    LocalDate dateInitial,
    LocalDate dateFinal,
    Double value,
    ContractStatus status,
    String comments,
    CompanyResponse customer,
    CompanyResponse supplier
) {}
