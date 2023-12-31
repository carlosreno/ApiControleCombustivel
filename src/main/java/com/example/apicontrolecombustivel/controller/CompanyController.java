package com.example.apicontrolecombustivel.controller;

import com.example.apicontrolecombustivel.dto.MessageDto;
import com.example.apicontrolecombustivel.dto.model.CompanyDto;
import com.example.apicontrolecombustivel.model.jpa.Company;
import com.example.apicontrolecombustivel.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping("/create")
    public ResponseEntity<Company> create(@Valid @RequestBody CompanyDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.create(dto));
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<Company>> findAll(){
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.findAll());
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<Company> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.findById(id));
    }@DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageDto> delete(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.delete(id));
    }@PutMapping("/put/{id}")
    public ResponseEntity<Company> put(@PathVariable Long id,CompanyDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.put(id,dto));
    }@PatchMapping("/patch/{id}")
    public ResponseEntity<Company> patch(@PathVariable Long id,CompanyDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.patch(id,dto));
    }
}
