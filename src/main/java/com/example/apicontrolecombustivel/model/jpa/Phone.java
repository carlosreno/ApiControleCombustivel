package com.example.apicontrolecombustivel.model.jpa;

import com.example.apicontrolecombustivel.enums.PhoneType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "phone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ddi;
    private String ddd;
    private String number;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private PhoneType phoneType;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
