package com.example.apicontrolecombustivel.model.jpa;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_sector")
@Data
public class UserSector {

    @EmbeddedId
    private UserSectorId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("sectorId")
    @JoinColumn(name = "sector_id")
    private Sector sector;
}
