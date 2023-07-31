package com.example.apicontrolecombustivel.model.jpa;

import com.example.apicontrolecombustivel.enums.ContractStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "contract")
@Data
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numberContract;

    @Column(length = 2000)
    private String object;

    private Date dateInitial;

    private Date dateFinal;

    private Double value;

    private String customer;

    @Enumerated(EnumType.STRING)
    private ContractStatus status;

    @Column(length = 2000)
    private String comments;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
}