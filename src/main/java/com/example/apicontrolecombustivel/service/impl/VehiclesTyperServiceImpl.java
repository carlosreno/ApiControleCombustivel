package com.example.apicontrolecombustivel.service.impl;

import com.example.apicontrolecombustivel.dto.MessageDto;
import com.example.apicontrolecombustivel.exception.BusinessException;
import com.example.apicontrolecombustivel.exception.NotFoundException;
import com.example.apicontrolecombustivel.model.jpa.TypeVehicles;
import com.example.apicontrolecombustivel.repositories.TypeVehiclesRepository;
import com.example.apicontrolecombustivel.service.VehiclesTypeService;
import com.example.apicontrolecombustivel.utils.MsgStandard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiclesTyperServiceImpl implements VehiclesTypeService {
    private final TypeVehiclesRepository vehiclesRepository;

    @Override
    public TypeVehicles create(TypeVehicles dto) {
        verifyIfNotExistTypeWithName(dto.getName());
        return vehiclesRepository.save(dto);
    }

    @Override
    public List<TypeVehicles> findAll() {
        return vehiclesRepository.findAll();
    }

    @Override
    public TypeVehicles findById(Long id) {
        return verifyIfExistTypeWithId(id);
    }

    @Override
    public MessageDto delete(Long id) {
        findById(id);
        vehiclesRepository.deleteById(id);
        return MsgStandard.msgStandardOk("deleted");
    }

    private TypeVehicles verifyIfExistTypeWithId(Long id) {
        return vehiclesRepository.findById(id).
                orElseThrow(()->new NotFoundException("userType with this name not exist"));
    }

    @Override
    public TypeVehicles put(TypeVehicles dto) {
        TypeVehicles userType = findById(dto.getId());
        verifyIfExistOtherTypeWithName(dto,userType.getName());
        return vehiclesRepository.save(userType);    }
    
    private void verifyIfExistOtherTypeWithName(TypeVehicles type,String nameDb) {
        if (!type.getName().equals(nameDb)){
            verifyIfNotExistTypeWithName(type.getName());
        }else {
            type.setName(nameDb);
        }
    }

    private void verifyIfNotExistTypeWithName(String name) {
        var optionalTypeVehicles = vehiclesRepository.findByName(name);
        if (optionalTypeVehicles.isPresent()){
            throw new BusinessException("userType whit this name already exist");
        }
    }
}