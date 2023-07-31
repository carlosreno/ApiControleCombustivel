package com.example.apicontrolecombustivel.service;

import com.example.apicontrolecombustivel.dto.model.CompanyDto;
import com.example.apicontrolecombustivel.model.jpa.Company;

import java.util.List;

public interface CompanyService {
    Company create(CompanyDto dto);
    List<Company> findAll();
    Company findById(Long id);
    Company put(Long id, CompanyDto dto);
    Company patch(Long id,CompanyDto dto);
}
