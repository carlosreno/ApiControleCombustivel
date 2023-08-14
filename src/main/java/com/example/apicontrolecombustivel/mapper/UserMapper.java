package com.example.apicontrolecombustivel.mapper;

import com.example.apicontrolecombustivel.dto.model.SectorDto;
import com.example.apicontrolecombustivel.dto.model.UserDto;
import com.example.apicontrolecombustivel.model.jpa.Company;
import com.example.apicontrolecombustivel.model.jpa.Sectors;
import com.example.apicontrolecombustivel.model.jpa.Users;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class UserMapper {
    public static Users fromDtoToEntity(Long id, UserDto dto, List<Sectors> sectors, Company company){
        return Users.builder()
                .id(id)
                .name(dto.name())
                .company(company)
                .sectors(sectors)
                .build();
    }
}
