package com.example.apicontrolecombustivel.model.jpa;

import com.example.apicontrolecombustivel.enums.OnOrOf;
import com.example.apicontrolecombustivel.repositories.AvailabilityRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.jpa.provider.PersistenceProvider;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

@Entity
@Table(name = "cars")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String renavam;

    private String placa;
    @ManyToOne
    @JoinColumn(name = "type_vehicles_id")
    private TypeVehicles typeVehicles;

    private String marca;

    private String brand;

    private Integer year;

    private String color;

    private String fuel;

    private Double mileage;

    private Date fabricationDate;

    @Column(length = 2000)
    private String comments;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "active_availability_id")
    private Availability activeAvailability;

    @Enumerated(EnumType.STRING)
    private OnOrOf status;
}
