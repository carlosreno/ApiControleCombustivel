package com.example.apicontrolecombustivel.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Builder
public record MessageDto(
        String message,
        HttpStatus httpStatus,
        Integer statusCode
)
{
}
