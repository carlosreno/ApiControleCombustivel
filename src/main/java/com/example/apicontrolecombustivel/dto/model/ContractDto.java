package com.example.apicontrolecombustivel.dto.model;

import com.example.apicontrolecombustivel.enums.ContractStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public record ContractDto(

        @NotNull
        String numberContract,
        @NotBlank
        String object,
        @NotNull
        @DateTimeFormat(pattern = "dd-MM-yyyy")
        LocalDate dateInitial,
        @NotNull
        @DateTimeFormat(pattern = "dd-MM-yyyy")
        LocalDate dateFinal,
        @NotNull
        Double value,
        @Enumerated(EnumType.STRING)
        ContractStatus status,
        @Max(value = 2000)
        String comments,
        @NotNull
        Long customerId,
        @NotNull
        Long supplierId,
        List<Long> contractItemsId
) {
}
