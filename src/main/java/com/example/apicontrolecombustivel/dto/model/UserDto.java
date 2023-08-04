package com.example.apicontrolecombustivel.dto.model;

import com.example.apicontrolecombustivel.model.jpa.Company;
import com.example.apicontrolecombustivel.model.jpa.Sectors;
import com.example.apicontrolecombustivel.model.jpa.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

public class UserDto {
    private String name;

    private Long company_id;
    private Long userType_id;

    private List<Long> sectors_ids;
}
