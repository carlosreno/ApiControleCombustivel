package com.example.apicontrolecombustivel.mapper;

import com.example.apicontrolecombustivel.dto.model.SectorDto;
import com.example.apicontrolecombustivel.model.jpa.Company;
import com.example.apicontrolecombustivel.model.jpa.Sectors;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SectorMapper {
    public static Sectors fromDtoToEntity(Long id, SectorDto dto, Company company){
        return Sectors.builder()
                .id(id)
                .name(dto.name())
                .company(company)
                .build();
    }
}
