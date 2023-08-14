package com.example.apicontrolecombustivel.dto.model;

import java.util.List;

public record UserDto (
        String name,

        Long companyId,
        Long userTypeId,

        List<Long> sectorIds
){}
