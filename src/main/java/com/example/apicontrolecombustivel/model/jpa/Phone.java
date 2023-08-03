package com.example.apicontrolecombustivel.model.jpa;

import com.example.apicontrolecombustivel.enums.PhoneType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "phone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ddi;
    private String ddd;
    private String number;

    @Enumerated(EnumType.STRING)
    private PhoneType phoneType;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = true)
    private User user;
    @ManyToOne
    @JoinColumn(name = "company_id",nullable = true)
    private Company company;
}
