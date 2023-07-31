package com.example.apicontrolecombustivel.service.impl;

import com.example.apicontrolecombustivel.dto.MessageDto;
import com.example.apicontrolecombustivel.dto.model.SectorDto;
import com.example.apicontrolecombustivel.exception.BusinessException;
import com.example.apicontrolecombustivel.exception.NotFoundException;
import com.example.apicontrolecombustivel.mapper.SectorMapper;
import com.example.apicontrolecombustivel.model.jpa.Company;
import com.example.apicontrolecombustivel.model.jpa.Sector;
import com.example.apicontrolecombustivel.repositories.SectorRepository;
import com.example.apicontrolecombustivel.service.CompanyService;
import com.example.apicontrolecombustivel.service.SectorService;
import com.example.apicontrolecombustivel.utils.MsgStandard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SectorServiceImpl implements SectorService {
    private final SectorRepository sectorRepository;
    private final CompanyService companyService;

    @Override
    public Sector create(SectorDto dto) {
        Company company = verifyIfExistCompanyAndReturn(dto.company_id());
        verifyIfNotExistSectorWithName(dto.name());
        Sector sector = SectorMapper.fromDtoToEntity(null,dto,company);
        return sectorRepository.save(sector);
    }

    @Override
    public List<Sector> findAll() {
        return sectorRepository.findAll();
    }

    @Override
    public Sector findById(Long id) {
        return verifyIfExistAndReturn(id);
    }

    @Override
    public MessageDto delete(Long id) {
        var sector = verifyIfExistAndReturn(id);
        sectorRepository.deleteById(sector.getId());
        return MsgStandard.msgStandardOk("deleted");
    }

    private Sector verifyIfExistAndReturn(Long id) {
        return sectorRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Sector with this id not exist")
        );
    }

    @Override
    public Sector put(Long id, SectorDto dto) {
        Company company = verifyIfExistCompanyAndReturn(dto.company_id());
        Sector sectorDb = findById(id);
        Sector sector = SectorMapper.fromDtoToEntity(id,dto,company);
        verifyIfNameBelongOtherRegister(sector, dto.name(), sectorDb.getName());
        return sectorRepository.save(sector);
    }

    @Override
    public Sector patch(Long id, SectorDto dto) {
        var sectorDb = findById(id);
        verifyIfExistCompanyAndReturn(dto.company_id());
        Sector sector = Sector.builder()
                .id(id)
                .company(dto.company_id() != null ?
                        verifyIfExistCompanyAndReturn(id) : sectorDb.getCompany())
                .name(dto.name() != null ? dto.name() : sectorDb.getName())
                .build();
        verifyIfNameBelongOtherRegister(sector, dto.name(), sectorDb.getName());
        return sectorRepository.save(sector);
    }
    private Company verifyIfExistCompanyAndReturn(Long id){
        return companyService.findById(id);
    }
    private Boolean verifyIfNotExistSectorWithName(String name){
        if (sectorRepository.findByName(name).isEmpty()){
            return true;
        }
        throw new BusinessException("sector with this name already exist");
    }
    private void verifyIfNameBelongOtherRegister(Sector sector,String name,String nameDb){
        if (name != null && !name.equals(nameDb)){
            if (Boolean.TRUE.equals(verifyIfNotExistSectorWithName(name))){
                sector.setName(name);
            }
            return;
        }
        sector.setName(nameDb);
    }

}
