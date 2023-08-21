package com.example.apicontrolecombustivel.repositories;

import com.example.apicontrolecombustivel.model.jpa.Contract;
import com.example.apicontrolecombustivel.projections.contracts.ContractProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    @Query("SELECT new com.example.apicontrolecombustivel.projections.contracts.ContractProjection(" +
            "c.id, c.numberContract, c.object, c.dateInitial, c.dateFinal, c.value, c.status, c.comments, " +
            "new com.example.apicontrolecombustivel.projections.contracts.CompanyResponse(" +
            "c.customer.id, c.customer.razaoSocial, c.customer.nomeFantasia, c.customer.cnpj, c.customer.companyType), " +
            "new com.example.apicontrolecombustivel.projections.contracts.CompanyResponse(" +
            "c.supplier.id, c.supplier.razaoSocial, c.supplier.nomeFantasia, c.supplier.cnpj, c.supplier.companyType)) " +
            "FROM Contract c where c.id = :contractId")
    ContractProjection findAllProjectedById(@Param("contractId") Long contractId);
    @Query("SELECT new com.example.apicontrolecombustivel.projections.contracts.ContractProjection(" +
            "c.id, c.numberContract, c.object, c.dateInitial, c.dateFinal, c.value, c.status, c.comments, " +
            "new com.example.apicontrolecombustivel.projections.contracts.CompanyResponse(" +
            "c.customer.id, c.customer.razaoSocial, c.customer.nomeFantasia, c.customer.cnpj, c.customer.companyType), " +
            "new com.example.apicontrolecombustivel.projections.contracts.CompanyResponse(" +
            "c.supplier.id, c.supplier.razaoSocial, c.supplier.nomeFantasia, c.supplier.cnpj, c.supplier.companyType)) " +
            "FROM Contract c")
    List<ContractProjection> findAllProjectedBy();

    @Query("SELECT c.id, c.numberContract, c.object, c.dateInitial, c.dateFinal, c.value, c.status, c.comments," +
            " c.customer.id, c.customer.razaoSocial, c.customer.nomeFantasia, c.customer.cnpj, c.customer.companyType," +
            " c.supplier.id, c.supplier.razaoSocial, c.supplier.nomeFantasia, c.supplier.cnpj, c.supplier.companyType FROM Contract c")
    List<Object[]> findAllContractInfo();

}
