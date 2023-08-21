package com.example.apicontrolecombustivel.projections.contracts;

import com.example.apicontrolecombustivel.enums.ContractStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractProjection {
    private Long id;
    private String numberContract;
    private String object;
    private LocalDate dateInitial;
    private LocalDate dateFinal;
    private Double value;
    private ContractStatus status;
    private String comments;
    private CompanyResponse customer;
    private CompanyResponse supplier;

}
