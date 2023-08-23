package com.example.apicontrolecombustivel.service.impl;

import com.example.apicontrolecombustivel.dto.MessageDto;
import com.example.apicontrolecombustivel.dto.model.CarDto;
import com.example.apicontrolecombustivel.enums.OnOrOf;
import com.example.apicontrolecombustivel.exception.NotFoundException;
import com.example.apicontrolecombustivel.mapper.CarMapper;
import com.example.apicontrolecombustivel.mapper.UserMapper;
import com.example.apicontrolecombustivel.model.jpa.*;
import com.example.apicontrolecombustivel.repositories.AvailabilityRepository;
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
    private final CompanyService companyService;
    private final VehiclesTypeService vehiclesTypeService;
    private final AvailabilityRepository availabilityRepository;
    @Override
    public Car create(CarDto dto) {
        Company company = getCompanyById(dto);
        TypeVehicles typeVehicles = getTypeById(dto);
        Car car = CarMapper.fromDtoToEntity(null,dto,company,typeVehicles);
        return carRepository.save(car);
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll().stream()
                .peek(this::setActiveAvailability).toList();
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
        var optionalCar = carRepository.findById(id);
        optionalCar.ifPresent(this::setActiveAvailability);
        return optionalCar.orElseThrow(()-> new  NotFoundException("Availability with this id not exist"));

    }

    private void setActiveAvailability(Car car) {
        availabilityRepository.findByCarIdAndStatus(car.getId(), OnOrOf.active)
                .ifPresentOrElse(car::setActiveAvailability,
                    ()-> car.setActiveAvailability(null));
    }

    @Override
    public Car put(Long id, CarDto dto) {
        returnIfExist(id);
        Company company = getCompanyById(dto);
        TypeVehicles typeVehicles = getTypeById(dto);
        Car car = CarMapper.fromDtoToEntity(id,dto,company,typeVehicles);
        return carRepository.save(car);
    }

    @Override
    public Car patch(Long id, CarDto dto) {
        Car carDb = returnIfExist(id);
        Company company = getCompanyById(dto);
        TypeVehicles typeVehicles = getTypeById(dto);
        Car newDataUser = Car.builder()
                .id(id)
                .placa(dto.placa() != null ? dto.placa() : carDb.getPlaca())
                .renavam(dto.renavam()!= null ? dto.renavam() : carDb.getRenavam())
                .typeVehicles(dto.typeVehicles() != null ? typeVehicles : carDb.getTypeVehicles())
                .brand(dto.brand() != null ? dto.brand() : carDb.getBrand())
                .color(dto.color() != null ? dto.color() : carDb.getColor())
                .mileage(dto.mileage() != null ? dto.mileage() : carDb.getMileage())
                .fabricationDate(dto.fabricationDate() != null ? dto.fabricationDate() : carDb.getFabricationDate())
                .comments(dto.comments() != null ? dto.comments() : carDb.getComments())
                .company(dto.companyId() != null ? company : carDb.getCompany())
                .status(dto.status() != null ? dto.status() : carDb.getStatus())
                .build();
        return carRepository.save(newDataUser);
    }

    private Company getCompanyById(CarDto dto) {
        return companyService.findById(dto.companyId());
    }


    private TypeVehicles getTypeById(CarDto dto) {
        return vehiclesTypeService.findById(dto.typeVehicles());
    }
}
