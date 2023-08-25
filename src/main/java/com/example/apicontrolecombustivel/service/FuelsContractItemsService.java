package com.example.apicontrolecombustivel.service;

import com.example.apicontrolecombustivel.dto.MessageDto;
import com.example.apicontrolecombustivel.dto.model.FuelsContractItemDTO;
import com.example.apicontrolecombustivel.model.jpa.FuelsContractItem;
import com.example.apicontrolecombustivel.projections.contracts.ItemContractProjection;

import java.util.List;
import java.util.Set;

public interface FuelsContractItemsService {
    List<FuelsContractItem> create(Long contractId,List<FuelsContractItemDTO> listDTOs);
    List<ItemContractProjection> findAllByContractId(Long id);
    FuelsContractItem findById(Long id);
    MessageDto delete(List<Long> ids);
    List<FuelsContractItem> put(Long contractId,List<FuelsContractItemDTO> fuelsContractItems);
    List<FuelsContractItem> patch(Long contractId,List<FuelsContractItemDTO> fuelsContractItems);
}
