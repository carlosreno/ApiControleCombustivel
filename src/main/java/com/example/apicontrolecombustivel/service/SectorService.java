package com.example.apicontrolecombustivel.service;

import com.example.apicontrolecombustivel.dto.MessageDto;
import com.example.apicontrolecombustivel.dto.model.SectorDto;
import com.example.apicontrolecombustivel.model.jpa.Sector;

import java.util.List;

public interface SectorService {
    Sector create(SectorDto dto);
    List<Sector> findAll();
    Sector findById(Long id);
    MessageDto delete(Long id);
    Sector put(Long id, SectorDto dto);
    Sector patch(Long id,SectorDto dto);
}
