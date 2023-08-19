package com.example.apicontrolecombustivel.mapper;

import java.util.List;

import com.example.apicontrolecombustivel.dto.model.UserDto;
import com.example.apicontrolecombustivel.model.jpa.Company;
import com.example.apicontrolecombustivel.model.jpa.Sectors;
import com.example.apicontrolecombustivel.model.jpa.UserType;
import com.example.apicontrolecombustivel.model.jpa.Users;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {
    public static Users fromDtoToEntity(Long id, UserDto dto,List<Sectors> sectors,
                                        UserType type, Company company){
        return Users.builder()
                .id(id)
                .name(dto.name())
                .userType(type)
                .company(company)
                .sectors(sectors)
                .build();
    }
}
