package com.example.apicontrolecombustivel.model.jpa;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "type_vehicles")
public class TypeVehicles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
