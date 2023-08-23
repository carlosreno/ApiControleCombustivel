package com.example.apicontrolecombustivel.repositories;

import com.example.apicontrolecombustivel.dto.projectionsDto.AvailabilityResponse;
import com.example.apicontrolecombustivel.enums.OnOrOf;
import com.example.apicontrolecombustivel.model.jpa.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    @Query("SELECT new com.example.apicontrolecombustivel.dto.projectionsDto.AvailabilityResponse(" +
            "a.id,a.sector.name, a.status) FROM Availability a WHERE a.car.id = :carId AND a.status = :status")
    Optional<AvailabilityResponse>
    findByCarIdAndStatus(@Param("carId") Long carId,@Param("status") OnOrOf onOrOf);
}
