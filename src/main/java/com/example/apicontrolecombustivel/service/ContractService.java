package com.example.apicontrolecombustivel.service;

import com.example.apicontrolecombustivel.dto.MessageDto;
import com.example.apicontrolecombustivel.dto.model.ContractDetailsDto;
import com.example.apicontrolecombustivel.dto.projectionsDto.ContractResponse;
import com.example.apicontrolecombustivel.model.jpa.Contract;
import com.example.apicontrolecombustivel.projections.contracts.ContractProjection;

import java.util.List;

public interface ContractService {
    Contract create(ContractDetailsDto dto);
    List<ContractProjection> findAllDetails();
    ContractProjection findDetailsContractById(Long id);
    List<ContractResponse> findAll();
    Contract findById(Long id);
    MessageDto delete(Long id);
    Contract put(Long id, ContractDetailsDto dto);
    Contract patch(Long id, ContractDetailsDto dto);
}
