package com.example.apicontrolecombustivel.controller;

import com.example.apicontrolecombustivel.dto.MessageDto;
import com.example.apicontrolecombustivel.dto.model.ContractDetailsDto;
import com.example.apicontrolecombustivel.model.jpa.Contract;
import com.example.apicontrolecombustivel.projections.contracts.ContractProjection;
import com.example.apicontrolecombustivel.service.ContractService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contracts")
@RequiredArgsConstructor
public class ContractController {
    private final ContractService contractService;

    @PostMapping("/create")
    public ResponseEntity<Contract> create(@Valid @RequestBody ContractDetailsDto dto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(contractService.create(dto));
    }
    @GetMapping("/findAllDetails")
    public ResponseEntity<List<ContractProjection>> findAllDetails(){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(contractService.findAllDetails());
    }
    @GetMapping("/findDetailsContractById/{id}")
    public ResponseEntity<ContractProjection> findAllDetails(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(contractService.findDetailsContractById(id));
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<com.example.apicontrolecombustivel.dto.projectionsDto.ContractResponse>> findAll(){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(contractService.findAll());
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<Contract> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(contractService.findById(id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageDto> delete(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(contractService.delete(id));
    }
    @PutMapping("/put/{id}")
    public ResponseEntity<Contract> put(@PathVariable Long id,@Valid @RequestBody ContractDetailsDto dto){
        return ResponseEntity.status(HttpStatus.OK)
                .body(contractService.put(id,dto));
    }
    @PatchMapping("/patch/{id}")
    public ResponseEntity<Contract> patch(@PathVariable Long id,@RequestBody ContractDetailsDto dto){
        return ResponseEntity.status(HttpStatus.OK)
                .body(contractService.patch(id,dto));
    }
}
