package com.example.apicontrolecombustivel.service;

import com.example.apicontrolecombustivel.dto.MessageDto;
import com.example.apicontrolecombustivel.dto.model.ContractDto;
import com.example.apicontrolecombustivel.model.jpa.Contract;

import java.util.List;

public interface ContractService {
    Contract create(ContractDto dto);
    List<Contract> findAll();
    Contract findById(Long id);
    MessageDto delete(Long id);
    Contract put(Long id, ContractDto dto);
    Contract patch(Long id,ContractDto dto);
}
