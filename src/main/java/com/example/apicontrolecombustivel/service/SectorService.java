package com.example.apicontrolecombustivel.service;

import com.example.apicontrolecombustivel.dto.MessageDto;
import com.example.apicontrolecombustivel.dto.model.SectorDto;
import com.example.apicontrolecombustivel.model.jpa.Sectors;

import java.util.List;
import java.util.Set;

public interface SectorService {
    Sectors create(SectorDto dto);
    List<Sectors> findAll();
    Sectors findById(Long id);
    List<Sectors> findAllById(Set<Long> sectorsId);
    MessageDto delete(Long id);
    Sectors put(Long id, SectorDto dto);
    Sectors patch(Long id,SectorDto dto);
}
