package com.example.apicontrolecombustivel.projections.contracts;

import com.example.apicontrolecombustivel.enums.CompanyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyResponse {
    private Long id;
    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
    private CompanyType companyType;

}
