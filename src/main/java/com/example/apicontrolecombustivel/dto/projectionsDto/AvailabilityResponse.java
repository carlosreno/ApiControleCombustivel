package com.example.apicontrolecombustivel.dto.projectionsDto;

import com.example.apicontrolecombustivel.enums.OnOrOf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailabilityResponse {
    private Long id;
    private String nameSector;
    private OnOrOf onOrOf;
}
