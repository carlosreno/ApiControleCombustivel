package com.example.apicontrolecombustivel.service;

import com.example.apicontrolecombustivel.dto.MessageDto;
import com.example.apicontrolecombustivel.dto.model.AvailabilityDto;
import com.example.apicontrolecombustivel.model.jpa.Availability;

import java.util.List;

public interface AvailabilityService {
    Availability create(AvailabilityDto dto);
    List<Availability> findAll();
    MessageDto delete(Long id);
    Availability findById(Long id);
    Availability put(Long id, AvailabilityDto dto);
    Availability patch(Long id,AvailabilityDto dto);
}
