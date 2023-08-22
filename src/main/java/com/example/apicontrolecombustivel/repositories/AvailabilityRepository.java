package com.example.apicontrolecombustivel.repositories;

import com.example.apicontrolecombustivel.enums.OnOrOf;
import com.example.apicontrolecombustivel.model.jpa.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    Optional<Availability> findByStatus(OnOrOf onOrOf);
}
