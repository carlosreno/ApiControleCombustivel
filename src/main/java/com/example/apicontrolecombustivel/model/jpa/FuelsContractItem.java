package com.example.apicontrolecombustivel.model.jpa;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "fuels_contract_items")
public class FuelsContractItem {

    @EmbeddedId
    private FuelsContractItemKey id;

    @Column(name = "amount")
    private int amount;

    @Column(name = "price_per_unit")
    private BigDecimal pricePerUnit;

    @Column(name = "total_cost")
    private BigDecimal totalCost;

}
