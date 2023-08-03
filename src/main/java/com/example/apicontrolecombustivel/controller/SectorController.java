package com.example.apicontrolecombustivel.controller;

import com.example.apicontrolecombustivel.dto.MessageDto;
import com.example.apicontrolecombustivel.dto.model.SectorDto;
import com.example.apicontrolecombustivel.model.jpa.Sectors;
import com.example.apicontrolecombustivel.service.SectorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sector")
@RequiredArgsConstructor
public class SectorController {
    private final SectorService sectorService;

    @PostMapping("/create")
    public ResponseEntity<Sectors> create(@Valid @RequestBody SectorDto dto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(sectorService.create(dto));
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<Sectors>> findAll(){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(sectorService.findAll());
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<Sectors> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(sectorService.findById(id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageDto> delete(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(sectorService.delete(id));
    }
    @PutMapping("/put/{id}")
    public ResponseEntity<Sectors> put(@PathVariable Long id,@Valid @RequestBody SectorDto dto){
        return ResponseEntity.status(HttpStatus.OK)
                .body(sectorService.put(id,dto));
    }
    @PatchMapping("/patch/{id}")
    public ResponseEntity<Sectors> patch(@PathVariable Long id,@RequestBody SectorDto dto){
        return ResponseEntity.status(HttpStatus.OK)
                .body(sectorService.patch(id,dto));
    }
}
