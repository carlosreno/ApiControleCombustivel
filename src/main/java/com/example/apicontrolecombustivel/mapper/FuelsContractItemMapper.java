package com.example.apicontrolecombustivel.mapper;

import com.example.apicontrolecombustivel.dto.model.FuelsContractItemDTO;
import com.example.apicontrolecombustivel.model.jpa.Contract;
import com.example.apicontrolecombustivel.model.jpa.Fuels;
import com.example.apicontrolecombustivel.model.jpa.FuelsContractItem;
import com.example.apicontrolecombustivel.model.jpa.FuelsContractItemKey;
import com.example.apicontrolecombustivel.utils.TotalCostCalculator;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

@UtilityClass
public class FuelsContractItemMapper {
    public static FuelsContractItem fromDtoToEntity(Fuels fuels, Contract contract,
                                                    FuelsContractItemDTO dto){
        FuelsContractItemKey fuelsContractItemKey = new FuelsContractItemKey();
        fuelsContractItemKey.setContractId(contract.getId());
        fuelsContractItemKey.setFuelId(fuels.getId());
        BigDecimal totalPrice = TotalCostCalculator
                .calculateTotalCost(BigDecimal.valueOf(dto.amount()),dto.pricePerUnit());
        return FuelsContractItem.builder()
                .id(fuelsContractItemKey)
                .fuels(fuels)
                .contract(contract)
                .pricePerUnit(dto.pricePerUnit())
                .totalCost(totalPrice)
                .amount(dto.amount())
                .build();
    }
}
