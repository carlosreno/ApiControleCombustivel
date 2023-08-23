package com.example.apicontrolecombustivel.repositories;

import com.example.apicontrolecombustivel.model.jpa.Fuels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuelRepository extends JpaRepository<Fuels, Long> {
    Optional<Fuels> findByName(String name);
}
