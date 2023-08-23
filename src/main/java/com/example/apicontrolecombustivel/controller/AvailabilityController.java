package com.example.apicontrolecombustivel.controller;

import com.example.apicontrolecombustivel.dto.MessageDto;
import com.example.apicontrolecombustivel.dto.model.AvailabilityDto;
import com.example.apicontrolecombustivel.model.jpa.Availability;
import com.example.apicontrolecombustivel.service.AvailabilityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/availability")
@RequiredArgsConstructor
public class AvailabilityController {
    private final AvailabilityService availabilityService;

    @PostMapping("/create")
    public ResponseEntity<Availability> create(@Valid @RequestBody AvailabilityDto dto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(availabilityService.create(dto));
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<Availability>> findAll(){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(availabilityService.findAll());
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<Availability> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(availabilityService.findById(id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageDto> delete(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(availabilityService.delete(id));
    }
    @PutMapping("/put/{id}")
    public ResponseEntity<Availability> put(@PathVariable Long id,@Valid @RequestBody AvailabilityDto dto){
        return ResponseEntity.status(HttpStatus.OK)
                .body(availabilityService.put(id,dto));
    }
    @PatchMapping("/patch/{id}")
    public ResponseEntity<Availability> patch(@PathVariable Long id,@RequestBody AvailabilityDto dto){
        return ResponseEntity.status(HttpStatus.OK)
                .body(availabilityService.patch(id,dto));
    }
}
