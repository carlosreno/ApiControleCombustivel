package com.example.apicontrolecombustivel.model.jpa;

import com.example.apicontrolecombustivel.enums.CompanyType;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "razao_social")
    private String razaoSocial;

    @Column(name = "nome_fantasia")
    private String nomeFantasia;

    private String cnpj;

    @Enumerated(EnumType.STRING)
    private CompanyType companyType;

    @OneToMany(mappedBy = "company",fetch = FetchType.LAZY)
    private List<Phone> phones;

    @OneToMany(mappedBy = "company",fetch = FetchType.LAZY)
    private List<Address> address;

    @OneToMany(mappedBy = "company",fetch = FetchType.LAZY)
    private List<Sectors> sector;
}
