package com.example.apicontrolecombustivel.service;

import com.example.apicontrolecombustivel.dto.MessageDto;
import com.example.apicontrolecombustivel.model.jpa.Fuels;

import java.util.List;

public interface FuelService {
    Fuels create(Fuels dto);
    List<Fuels> findAll();
    MessageDto delete(Long id);
    Fuels findById(Long id);
    Fuels put(Fuels dto);}
