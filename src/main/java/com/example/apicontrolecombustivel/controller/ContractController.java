package com.example.apicontrolecombustivel.controller;

import com.example.apicontrolecombustivel.dto.MessageDto;
import com.example.apicontrolecombustivel.dto.model.ContractDto;
import com.example.apicontrolecombustivel.model.jpa.Contract;
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
    public ResponseEntity<Contract> create(@Valid @RequestBody ContractDto dto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(contractService.create(dto));
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<Contract>> findAll(){
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
    public ResponseEntity<Contract> put(@PathVariable Long id,@Valid @RequestBody ContractDto dto){
        return ResponseEntity.status(HttpStatus.OK)
                .body(contractService.put(id,dto));
    }
    @PatchMapping("/patch/{id}")
    public ResponseEntity<Contract> patch(@PathVariable Long id,@RequestBody ContractDto dto){
        return ResponseEntity.status(HttpStatus.OK)
                .body(contractService.patch(id,dto));
    }
}
