package com.example.apicontrolecombustivel.model.jpa;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "fuels")
public class Fuels {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String unit;

}
