package com.example.apicontrolecombustivel.model.jpa;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_type")
@Data
public class UserType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
