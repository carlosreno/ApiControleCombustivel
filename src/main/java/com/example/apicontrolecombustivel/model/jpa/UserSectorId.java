package com.example.apicontrolecombustivel.model.jpa;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
class UserSectorId implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "sector_id")
    private Long sectorId;
}
