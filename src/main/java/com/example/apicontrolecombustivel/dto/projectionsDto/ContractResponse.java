package com.example.apicontrolecombustivel.dto.projectionsDto;

import com.example.apicontrolecombustivel.enums.ContractStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDate;
@Builder
public record ContractResponse(
    Long id,
    String numberContract,
    String object,
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dateInitial,
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dateFinal,
    Double value,
    ContractStatus status,
    String comments,
    CompanyResponse customer,
    CompanyResponse supplier
) {}
