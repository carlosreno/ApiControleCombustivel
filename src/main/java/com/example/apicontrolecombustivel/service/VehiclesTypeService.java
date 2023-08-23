package com.example.apicontrolecombustivel.service;

import com.example.apicontrolecombustivel.dto.MessageDto;
import com.example.apicontrolecombustivel.model.jpa.TypeVehicles;

import java.util.List;

public interface VehiclesTypeService {
    TypeVehicles create(TypeVehicles dto);
    List<TypeVehicles> findAll();
    MessageDto delete(Long id);
    TypeVehicles findById(Long id);
    TypeVehicles put(TypeVehicles dto);
}
