package com.example.apicontrolecombustivel.repositories;

import com.example.apicontrolecombustivel.model.jpa.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {
    Optional<Sector> findByName(String name);
}
