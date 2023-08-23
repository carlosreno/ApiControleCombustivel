package com.example.apicontrolecombustivel.service.impl;

import com.example.apicontrolecombustivel.dto.MessageDto;
import com.example.apicontrolecombustivel.dto.model.AvailabilityDto;
import com.example.apicontrolecombustivel.exception.NotFoundException;
import com.example.apicontrolecombustivel.mapper.AvailabilityMapper;
import com.example.apicontrolecombustivel.mapper.UserMapper;
import com.example.apicontrolecombustivel.model.jpa.Availability;
import com.example.apicontrolecombustivel.model.jpa.Car;
import com.example.apicontrolecombustivel.model.jpa.Company;
import com.example.apicontrolecombustivel.model.jpa.Sectors;
import com.example.apicontrolecombustivel.repositories.AvailabilityRepository;
import com.example.apicontrolecombustivel.service.AvailabilityService;
import com.example.apicontrolecombustivel.service.CarService;
import com.example.apicontrolecombustivel.service.SectorService;
import com.example.apicontrolecombustivel.utils.MsgStandard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvailabilityServiceImpl implements AvailabilityService {
    private final AvailabilityRepository availabilityRepository;
    private final SectorService sectorService;
    private final CarService carService;

    @Override
    public Availability create(AvailabilityDto dto) {
        Car car = getCarById(dto);
        Sectors sectors = getSectorById(dto.sectorId());
        Availability availability = AvailabilityMapper.fromDtoToEntity(null,sectors,car,dto);
        return availabilityRepository.save(availability);
    }

    @Override
    public List<Availability> findAll() {
        return availabilityRepository.findAll();
    }

    @Override
    public MessageDto delete(Long id) {
        returnIfExist(id);
        availabilityRepository.deleteById(id);
        return MsgStandard.msgStandardOk("deleted");
    }

    @Override
    public Availability findById(Long id) {
        return returnIfExist(id);
    }

    private Availability returnIfExist(Long id) {
       return availabilityRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Availability with this id not exist"));
    }

    @Override
    public Availability put(Long id, AvailabilityDto dto) {
        returnIfExist(id);
        Car car = getCarById(dto);
        Sectors sectors = getSectorById(dto.sectorId());
        Availability availability = AvailabilityMapper.fromDtoToEntity(id,sectors,car,dto);
        return availabilityRepository.save(availability);
    }

    @Override
    public Availability patch(Long id, AvailabilityDto dto) {
        Availability availabilityDb = returnIfExist(id);

        Availability newDataUser = Availability.builder()
                .id(id)
                .car(dto.carId() != null ? getCarById(dto) : availabilityDb.getCar())
                .sector(dto.sectorId() != null ? getSectorById(dto.sectorId()) : availabilityDb.getSector())
                .status(dto.status() != null ? dto.status() : availabilityDb.getStatus())
                .build();
        return availabilityRepository.save(newDataUser);
    }


    private Sectors getSectorById(Long sectorId) {
        return sectorService.findById(sectorId);
    }

    private Car getCarById(AvailabilityDto dto) {
        return carService.findById(dto.carId());
    }
}
