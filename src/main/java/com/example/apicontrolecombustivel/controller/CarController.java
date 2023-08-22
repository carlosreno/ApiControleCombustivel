package com.example.apicontrolecombustivel.controller;

import com.example.apicontrolecombustivel.dto.MessageDto;
import com.example.apicontrolecombustivel.dto.model.CarDto;
import com.example.apicontrolecombustivel.model.jpa.CarDto;
import com.example.apicontrolecombustivel.model.jpa.Car;
import com.example.apicontrolecombustivel.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @PostMapping("/create")
    public ResponseEntity<Car> create(@Valid @RequestBody CarDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.create(dto));
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<Car>> findAll(){
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.findAll());
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<Car> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.findById(id));
    }@DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageDto> delete(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.delete(id));
    }@PutMapping("/put/{id}")
    public ResponseEntity<Car> put(@PathVariable Long id,CarDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.put(id,dto));
    }@PatchMapping("/patch/{id}")
    public ResponseEntity<Car> patch(@PathVariable Long id,CarDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.patch(id,dto));
    }
}
