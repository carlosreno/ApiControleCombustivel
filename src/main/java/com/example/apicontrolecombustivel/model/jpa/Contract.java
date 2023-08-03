package com.example.apicontrolecombustivel.model.jpa;

import com.example.apicontrolecombustivel.enums.ContractStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "contracts")
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

    @Enumerated(EnumType.STRING)
    private ContractStatus status;

    @Column(length = 2000)
    private String comments;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Company customer;

    @OneToMany
    private List<FuelsContractItem> contractItems;

}