package com.example.apicontrolecombustivel.model.jpa;

import com.example.apicontrolecombustivel.enums.AvailabilityStatus;
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
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "sector_id")
    private Sector sector;

    @Enumerated(EnumType.STRING)
    private AvailabilityStatus status;
}