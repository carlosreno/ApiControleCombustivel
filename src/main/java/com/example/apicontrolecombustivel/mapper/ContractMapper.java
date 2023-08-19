package com.example.apicontrolecombustivel.mapper;

import com.example.apicontrolecombustivel.dto.model.ContractDto;
import com.example.apicontrolecombustivel.dto.model.UserDto;
import com.example.apicontrolecombustivel.model.jpa.*;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ContractMapper {
    public static Contract fromDtoToEntity
            (Long id, ContractDto dto,Company customer, Company supplier){
        return Contract.builder()
                .id(id)
                .numberContract(dto.numberContract())
                .object(dto.object())
                .status(dto.status())
                .customer(customer)
                .supplier(supplier)
                .comments(dto.comments())
                .dateInitial(dto.dateInitial())
                .dateFinal(dto.dateFinal())
                .build();
    }
}
