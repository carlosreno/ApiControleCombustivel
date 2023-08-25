package com.example.apicontrolecombustivel.controller;

import com.example.apicontrolecombustivel.dto.MessageDto;
import com.example.apicontrolecombustivel.dto.model.FuelsContractItemDTO;
import com.example.apicontrolecombustivel.dto.model.UserDto;
import com.example.apicontrolecombustivel.model.jpa.FuelsContractItem;
import com.example.apicontrolecombustivel.model.jpa.Users;
import com.example.apicontrolecombustivel.projections.contracts.ItemContractProjection;
import com.example.apicontrolecombustivel.service.FuelsContractItemsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items-fuel-contract")
@RequiredArgsConstructor
public class ItemsContractController {
    private final FuelsContractItemsService itemsService;

    @PostMapping("/create/{contractId}")
    public ResponseEntity<List<FuelsContractItem>> create(
        @PathVariable Long contractId,@Valid @RequestBody List<FuelsContractItemDTO> contractItemDTOS){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(itemsService.create(contractId,contractItemDTOS));
    }
    @GetMapping("/findAllByContractId/{id}")
    public ResponseEntity<List<ItemContractProjection>> findAllByContractId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(itemsService.findAllByContractId(id));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<FuelsContractItem> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(itemsService.findById(id));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<MessageDto> delete(@RequestBody List<Long> ids){
        return ResponseEntity.status(HttpStatus.OK)
                .body(itemsService.delete(ids));
    }
    @PutMapping("/put/{contractId}")
    public ResponseEntity<List<FuelsContractItem>> put(@PathVariable Long contractId,@Valid @RequestBody List<FuelsContractItemDTO> DTOs){
        return ResponseEntity.status(HttpStatus.OK)
                .body(itemsService.put(contractId,DTOs));
    }
    @PatchMapping("/patch/{contractId}")
    public ResponseEntity<List<FuelsContractItem>> patch(
            @PathVariable Long contractId,@RequestBody List<FuelsContractItemDTO> DTOs){
        return ResponseEntity.status(HttpStatus.OK)
                .body(itemsService.patch(contractId,DTOs));
    }
}
