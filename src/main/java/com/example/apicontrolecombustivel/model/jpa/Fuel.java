package com.example.apicontrolecombustivel.model.jpa;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "fuels")
public class Fuel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String unit;

}
