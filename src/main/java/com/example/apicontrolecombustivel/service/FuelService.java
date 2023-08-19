package com.example.apicontrolecombustivel.service;

import com.example.apicontrolecombustivel.dto.MessageDto;
import com.example.apicontrolecombustivel.model.jpa.Fuel;
import com.example.apicontrolecombustivel.model.jpa.UserType;

import java.util.List;

public interface FuelService {
    Fuel create(Fuel dto);
    List<Fuel> findAll();
    MessageDto delete(Long id);
    Fuel findById(Long id);
    Fuel put(Fuel dto);}
