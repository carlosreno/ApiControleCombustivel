package com.example.apicontrolecombustivel.model.jpa;

import com.example.apicontrolecombustivel.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date requestDate;

    @ManyToOne
    @JoinColumn(name = "fuels_contract")
    private Fuels fuelsContract;

    private Double fuelAmount;

    @Column(length = 2000)
    private String comments;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "requester_id")
    private Users requester;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;
}