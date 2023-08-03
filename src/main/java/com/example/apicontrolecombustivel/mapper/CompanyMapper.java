package com.example.apicontrolecombustivel.mapper;

import com.example.apicontrolecombustivel.dto.model.CompanyDto;
import com.example.apicontrolecombustivel.model.jpa.Company;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CompanyMapper {
    public static Company fromDtoToEntity(Long id, CompanyDto dto){
        return Company.builder()
                .id(id)
                .cnpj(dto.cnpj())
                .name(dto.name())
                .email(dto.email())
                .build();
    }
}
