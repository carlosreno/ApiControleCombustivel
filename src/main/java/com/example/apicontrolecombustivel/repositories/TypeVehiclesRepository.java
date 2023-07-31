package com.example.apicontrolecombustivel.repositories;

import com.example.apicontrolecombustivel.model.jpa.Car;
import com.example.apicontrolecombustivel.model.jpa.TypeVehicles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeVehiclesRepository extends JpaRepository<TypeVehicles, Long> {
}
