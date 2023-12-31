package com.example.apicontrolecombustivel.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.util.List;
@Builder
public record ErrorDto(
        HttpStatus httpStatus,
        Integer HttpStatusCode,
        List<String> messages
) {
}
