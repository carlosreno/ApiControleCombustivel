package com.example.apicontrolecombustivel.model.jpa;

import com.example.apicontrolecombustivel.enums.OnOrOf;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "availability")
@Data
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "sector_id")
    private Sectors sector;

    @Enumerated(EnumType.STRING)
    private OnOrOf status;
}