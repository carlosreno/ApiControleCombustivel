package com.example.apicontrolecombustivel.model.jpa;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "user")
@Data
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "user_type")
    private UserType userType;

    @ManyToMany(mappedBy = "users")
    private List<Sectors> sectors;
}
