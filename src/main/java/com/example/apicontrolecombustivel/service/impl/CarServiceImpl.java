package com.example.apicontrolecombustivel.service.impl;

import com.example.apicontrolecombustivel.dto.MessageDto;
import com.example.apicontrolecombustivel.dto.model.CarDto;
import com.example.apicontrolecombustivel.exception.NotFoundException;
import com.example.apicontrolecombustivel.mapper.UserMapper;
import com.example.apicontrolecombustivel.model.jpa.*;
import com.example.apicontrolecombustivel.repositories.CarRepository;
import com.example.apicontrolecombustivel.service.*;
import com.example.apicontrolecombustivel.utils.MsgStandard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final AvailabilityS availability;
    private final CompanyService companyService;
    private final VehiclesTypeService vehiclesTypeService;

    @Override
    public Car create(CarDto dto) {
        Company company = getCompanyById(dto);
        TypeVehicles typeVehicles = getTypeById(dto);
        Car car = UserMapper.fromDtoToEntity(null,dto,sectors,userType,company);
        return carRepository.save(users);
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public MessageDto delete(Long id) {
        returnIfExist(id);
        carRepository.deleteById(id);
        return MsgStandard.msgStandardOk("deleted");
    }

    @Override
    public Car findById(Long id) {
        return returnIfExist(id);
    }

    private Car returnIfExist(Long id) {
       return carRepository.findById(id)
                .orElseThrow(()->new NotFoundException("user with this id not exist"));
    }

    @Override
    public Car put(Long id, CarDto dto) {
        returnIfExist(id);
        Company company = getCompanyById(dto);
        TypeVehicles userType = getTypeById(dto);
        List<Sectors> sectors = getAllSectorsById(dto.sectorIds());
        Car users = UserMapper.fromDtoToEntity(id,dto,sectors,userType,company);
        return carRepository.save(users);
    }

    @Override
    public Car patch(Long id, CarDto dto) {
        Car usersDb = returnIfExist(id);
        Car newDataUser = Car.builder()
                .id(id)
                .name(dto.name() != null ? dto.name() : usersDb.getName())
                .userType(dto.userTypeId() != null ? getTypeById(dto) : usersDb.getTypeVehicles())
                .sectors(dto.sectorIds() != null ? getAllSectorsById(dto.sectorIds()) : usersDb.getSectors())
                .company(dto.companyId() != null ? getCompanyById(dto) : usersDb.getCompany())
                .build();
        return carRepository.save(newDataUser);
    }

    private Company getCompanyById(CarDto dto) {
        return companyService.findById(dto.companyId());
    }

    private List<Sectors> getAllSectorsById(Set<Long> sectorIds) {
        return sectorService.findAllById(sectorIds);
    }

    private TypeVehicles getTypeById(CarDto dto) {
        return vehiclesTypeService.findById(dto.userTypeId());
    }
}
