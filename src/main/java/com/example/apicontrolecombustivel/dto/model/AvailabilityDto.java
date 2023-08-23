package com.example.apicontrolecombustivel.dto.model;

import com.example.apicontrolecombustivel.enums.OnOrOf;

public record AvailabilityDto(
        Long id,

        Long carId,

        Long sectorId,

        OnOrOf status
){


}