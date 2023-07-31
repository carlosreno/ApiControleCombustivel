package com.example.apicontrolecombustivel.mapper;

import com.example.apicontrolecombustivel.dto.model.SectorDto;
import com.example.apicontrolecombustivel.model.jpa.Company;
import com.example.apicontrolecombustivel.model.jpa.Sector;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SectorMapper {
    public static Sector fromDtoToEntity(Long id, SectorDto dto, Company company){
        return Sector.builder()
                .id(id)
                .name(dto.name())
                .company(company)
                .build();
    }
}
