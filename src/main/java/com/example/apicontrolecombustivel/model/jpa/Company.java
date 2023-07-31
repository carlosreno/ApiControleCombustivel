package com.example.apicontrolecombustivel.model.jpa;

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
@Table(name = "company")
public class Company {

    @Id
    @Column(name = "id")
    private Long id;
    private String name;
    private String cnpj;
    private String address;
    private String phone;
    private String email;
    @OneToMany(mappedBy = "company",fetch = FetchType.EAGER)
    private List<Sector> sector;
}
