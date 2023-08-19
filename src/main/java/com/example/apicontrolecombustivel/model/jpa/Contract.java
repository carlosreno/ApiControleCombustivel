package com.example.apicontrolecombustivel.model.jpa;

import com.example.apicontrolecombustivel.enums.ContractStatus;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@Table(name = "contracts")
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numberContract;

    @Column(length = 2000)
    private String object;

    private LocalDate dateInitial;

    private LocalDate dateFinal;

    private Double value;

    @Enumerated(EnumType.STRING)
    private ContractStatus status;

    @Column(length = 2000)
    private String comments;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Company customer;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Company supplier;

    @OneToMany(mappedBy = "contract",fetch = FetchType.LAZY)
    private List<FuelsContractItem> contractItems;

}