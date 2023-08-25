package com.example.apicontrolecombustivel.repositories;

import com.example.apicontrolecombustivel.model.jpa.FuelsContractItem;
import com.example.apicontrolecombustivel.projections.contracts.ItemContractProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuelsContractItemsRepository extends JpaRepository<FuelsContractItem, Long> {
    @Query("SELECT new com.example.apicontrolecombustivel.projections.contracts.ItemContractProjection(" +
            "f.contract.numberContract,f.fuels.name,f.amount,f.pricePerUnit,f.totalCost) from FuelsContractItem f where f.contract.id=:contractId")
    List<ItemContractProjection> findAllByContractId(@Param("contractId") Long id);
}
