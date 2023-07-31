package com.example.apicontrolecombustivel.model.jpa;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sector")
@Data
public class Sector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
