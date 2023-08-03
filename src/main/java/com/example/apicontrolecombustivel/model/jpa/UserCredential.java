package com.example.apicontrolecombustivel.model.jpa;

import com.example.apicontrolecombustivel.enums.OnOrOf;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user_credential")
public class UserCredential{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Users users;
    private String userName;
    private String passwordHash;
    private OnOrOf status;
}
