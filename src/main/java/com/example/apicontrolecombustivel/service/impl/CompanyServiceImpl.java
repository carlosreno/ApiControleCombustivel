package com.example.apicontrolecombustivel.service.impl;

import com.example.apicontrolecombustivel.dto.MessageDto;
import com.example.apicontrolecombustivel.dto.model.CompanyDto;
import com.example.apicontrolecombustivel.exception.NotFoundException;
import com.example.apicontrolecombustivel.mapper.CompanyMapper;
import com.example.apicontrolecombustivel.model.jpa.Company;
import com.example.apicontrolecombustivel.repositories.CompanyRepository;
import com.example.apicontrolecombustivel.service.CompanyService;
import com.example.apicontrolecombustivel.utils.MsgStandard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    @Override
    public Company create(CompanyDto dto) {
        Company company = CompanyMapper.fromDtoToEntity(null,dto);
        return companyRepository.save(company);
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public MessageDto delete(Long id) {
        verifyIfExistAndReturn(id);
        companyRepository.deleteById(id);
        return MsgStandard.msgStandardOk("deleted");
    }

    @Override
    public Company findById(Long id) {
        return verifyIfExistAndReturn(id);
    }

    private Company verifyIfExistAndReturn(Long id) {
        return companyRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Company with this id not exist")
        );
    }

    @Override
    public Company put(Long id, CompanyDto dto) {
        findById(id);
        Company company = CompanyMapper.fromDtoToEntity(id,dto);
        return companyRepository.save(company);
    }

    @Override
    public Company patch(Long id, CompanyDto dto) {
        var companyDb = findById(id);
        Company company = Company.builder()
                .id(id)
                .name(dto.name() != null ? dto.name() : companyDb.getName())
                .cnpj(dto.cnpj() != null ? dto.cnpj() : companyDb.getCnpj())
                .email(dto.email() != null ? dto.email() : companyDb.getEmail())
                .phone(dto.phone() != null ? dto.phone() : companyDb.getPhone())
                .address(dto.address() != null ? dto.address() : companyDb.getAddress())
                .build();
        return companyRepository.save(company);
    }
}
