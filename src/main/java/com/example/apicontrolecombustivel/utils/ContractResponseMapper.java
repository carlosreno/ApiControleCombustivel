package com.example.apicontrolecombustivel.utils;

import com.example.apicontrolecombustivel.dto.projectionsDto.CompanyResponse;
import com.example.apicontrolecombustivel.dto.projectionsDto.ContractResponse;
import com.example.apicontrolecombustivel.enums.CompanyType;
import com.example.apicontrolecombustivel.enums.ContractStatus;

import java.time.LocalDate;

public class ContractResponseMapper {

    public static ContractResponse mapToContractResponse(Object[] row) {
        var customer = CompanyResponse.builder()
                .id((Long) row[8])
                .razaoSocial((String) row[9])
                .nomeFantasia((String) row[10])
                .cnpj((String) row[11])
                .companyType((CompanyType) row[12])
                .build();

        var supplier = CompanyResponse.builder()
                .id((Long) row[13])
                .razaoSocial((String) row[14])
                .nomeFantasia((String) row[15])
                .cnpj((String) row[16])
                .companyType((CompanyType) row[17])
                .build();

        return ContractResponse.builder()
                .id((Long) row[0])
                .numberContract((String) row[1])
                .object((String) row[2])
                .dateInitial((LocalDate) row[3])
                .dateFinal((LocalDate) row[4])
                .value((Double) row[5])
                .status((ContractStatus) row[6])
                .comments((String) row[7])
                .customer(customer)
                .supplier(supplier)
                .build();
    }
}
