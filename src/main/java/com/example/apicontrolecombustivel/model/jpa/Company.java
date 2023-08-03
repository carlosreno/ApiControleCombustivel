package com.example.apicontrolecombustivel.model.jpa;

import com.example.apicontrolecombustivel.enums.CompanyType;
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
    @Column(name = "id")
    private Long id;
    private String name;
    private String cnpj;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CompanyType companyType;

    @OneToMany
    private List<Phone> phones;

    @OneToMany
    private List<Address> address;

    private String email;

    @OneToMany(mappedBy = "company",fetch = FetchType.EAGER)
    private List<Sector> sector;
}
