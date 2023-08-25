package com.example.apicontrolecombustivel.service.impl;

import com.example.apicontrolecombustivel.dto.MessageDto;
import com.example.apicontrolecombustivel.dto.model.FuelsContractItemDTO;
import com.example.apicontrolecombustivel.exception.BusinessException;
import com.example.apicontrolecombustivel.mapper.FuelsContractItemMapper;
import com.example.apicontrolecombustivel.model.jpa.Contract;
import com.example.apicontrolecombustivel.model.jpa.Fuels;
import com.example.apicontrolecombustivel.model.jpa.FuelsContractItem;
import com.example.apicontrolecombustivel.model.jpa.FuelsContractItemKey;
import com.example.apicontrolecombustivel.projections.contracts.ItemContractProjection;
import com.example.apicontrolecombustivel.repositories.FuelsContractItemsRepository;
import com.example.apicontrolecombustivel.service.ContractService;
import com.example.apicontrolecombustivel.service.FuelService;
import com.example.apicontrolecombustivel.service.FuelsContractItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class FuelsContractItemsServiceImpl implements FuelsContractItemsService {
    private final FuelsContractItemsRepository itemsRepository;
    private final ContractService contractService;
    private final FuelService fuelService;
    @Override
    public List<FuelsContractItem> create(Long contractId,List<FuelsContractItemDTO> listDTOs) {
        Contract contract = getContractById(contractId);
        List<Long> toFind = listDTOs.stream().map(FuelsContractItemDTO::fuelId).toList();
        List<Long> repeated = toFind.stream()
                .filter(aLong -> {
                    List<Long> itemsContract = contract.getContractItems().stream()
                            .map(FuelsContractItem::getId)
                            .map(FuelsContractItemKey::getFuelId).toList();

                    return itemsContract.contains(aLong);
                }).toList();
        if (!repeated.isEmpty()){
            throw new BusinessException("contract already have fuels with this IDs"+repeated.toString());
        }
        List<FuelsContractItem> fuelsContractItems = listDTOs.stream()
                .map(fuelsContractItemDTO ->
                        builderFuelsContractItem(contract,fuelsContractItemDTO)
                ).toList();
        return itemsRepository.saveAll(fuelsContractItems);
    }

    @Override
    public List<ItemContractProjection> findAllByContractId(Long id) {
        return itemsRepository.findAllByContractId(id);
    }

    @Override
    public FuelsContractItem findById(Long id) {
        return null;
    }

    @Override
    public MessageDto delete(List<Long> ids) {
        return null;
    }

    @Override
    public List<FuelsContractItem> put(Long contractId,List<FuelsContractItemDTO> fuelsContractItems) {
        return null;
    }

    @Override
    public List<FuelsContractItem> patch(Long contractId,List<FuelsContractItemDTO> fuelsContractItems) {
        return null;
    }
    private FuelsContractItem builderFuelsContractItem(Contract contract,FuelsContractItemDTO fuelsContractItemDTO){
        Fuels fuels = getFuelById(fuelsContractItemDTO);
        return FuelsContractItemMapper.fromDtoToEntity(fuels,contract,fuelsContractItemDTO);
    }

    private Contract getContractById(Long id) {
        return contractService.findById(id);
    }

    private Fuels getFuelById(FuelsContractItemDTO fuelsContractItemDTO) {
        return fuelService.findById(fuelsContractItemDTO.fuelId());
    }
}
