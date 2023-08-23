package com.example.apicontrolecombustivel.dto.model;

import com.example.apicontrolecombustivel.enums.ContractStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
@Builder
public record ContractDetailsDto(
        @NotNull
        String numberContract,
        @NotBlank
        String object,
        @NotNull
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dateInitial,
        @NotNull
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dateFinal,
        @NotNull
        Double value,
        @Enumerated(EnumType.STRING)
        ContractStatus status,
        @Size(min =0,max = 2000)
        String comments,
        @NotNull
        Long customerId,
        @NotNull
        Long supplierId,
        List<Long> contractItemsId
) {
}
