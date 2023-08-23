package com.example.apicontrolecombustivel.model.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "fuels_contract_items")
public class FuelsContractItem {

    @EmbeddedId
    private FuelsContractItemKey id;

    @ManyToOne
    @JsonIgnore
    @MapsId("contractId")
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @ManyToOne
    @MapsId("fuelId")
    @JoinColumn(name = "fuel_id")
    private Fuels fuels;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "price_per_unit", nullable = false)
    private BigDecimal pricePerUnit;

    @Column(name = "total_cost", nullable = false)
    private BigDecimal totalCost;
}
