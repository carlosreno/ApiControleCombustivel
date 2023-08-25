package com.example.apicontrolecombustivel.projections.contracts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemContractProjection {
    private String numberContract;
    private String fuel;
    private Integer amount;
    private BigDecimal pricePerUnit;
    private BigDecimal totalCost;

}
