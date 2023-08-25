package com.example.apicontrolecombustivel.utils;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

@UtilityClass
public class TotalCostCalculator {
    public BigDecimal calculateTotalCost(BigDecimal amount,BigDecimal priceUnit){
        return amount.multiply(priceUnit);
    }
}
