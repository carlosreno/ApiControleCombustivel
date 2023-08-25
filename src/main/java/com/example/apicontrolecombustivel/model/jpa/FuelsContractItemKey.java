package com.example.apicontrolecombustivel.model.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;


@Data
@Embeddable
public class FuelsContractItemKey implements Serializable {

    @Column(name = "contract_id")
    private Long contractId;

    @Column(name = "fuel_id")
    private Long fuelId;

}
