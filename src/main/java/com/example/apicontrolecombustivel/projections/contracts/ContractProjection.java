package com.example.apicontrolecombustivel.projections.contracts;

import com.example.apicontrolecombustivel.enums.ContractStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractProjection {
    private Long id;
    private String numberContract;
    private String object;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateInitial;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateFinal;
    private Double value;
    private ContractStatus status;
    private String comments;
    private CompanyResponse customer;
    private CompanyResponse supplier;

}
