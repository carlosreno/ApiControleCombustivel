package com.example.apicontrolecombustivel.repositories;

import com.example.apicontrolecombustivel.model.jpa.Fuel;
import com.example.apicontrolecombustivel.model.jpa.Fueling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuelRepository extends JpaRepository<Fuel, Long> {
    Optional<Fuel> findByName(String name);
}
