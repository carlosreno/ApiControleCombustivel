package com.example.apicontrolecombustivel.model.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class FuelsContractItemKey implements Serializable {

    @Column(name = "contract_id")
    private Long contractId;

    @Column(name = "fuel_id")
    private Long fuelId;

}
