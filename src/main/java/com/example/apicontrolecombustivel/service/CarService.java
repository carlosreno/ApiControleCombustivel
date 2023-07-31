package com.example.apicontrolecombustivel.service;

import com.example.apicontrolecombustivel.dto.MessageDto;
import com.example.apicontrolecombustivel.dto.model.CarDto;
import com.example.apicontrolecombustivel.model.jpa.Car;
import com.example.apicontrolecombustivel.model.jpa.Sector;

import java.util.List;

public interface CarService {
    Car create(CarDto dto);
    List<Car> findAll();
    Car findById(Long id);
    MessageDto delete(Long id);
    Car put(Long id, CarDto dto);
    Car patch(Long id,CarDto dto);
}
