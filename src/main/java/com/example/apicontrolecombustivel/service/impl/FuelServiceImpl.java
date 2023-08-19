package com.example.apicontrolecombustivel.service.impl;

import com.example.apicontrolecombustivel.dto.MessageDto;
import com.example.apicontrolecombustivel.exception.BusinessException;
import com.example.apicontrolecombustivel.exception.NotFoundException;
import com.example.apicontrolecombustivel.model.jpa.Fuel;
import com.example.apicontrolecombustivel.repositories.FuelRepository;
import com.example.apicontrolecombustivel.service.FuelService;
import com.example.apicontrolecombustivel.utils.MsgStandard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FuelServiceImpl implements FuelService {
    private final FuelRepository fuelRepository;

    @Override
    public Fuel create(Fuel dto) {
        verifyIfNotExistTypeWithName(dto.getName());
        return fuelRepository.save(dto);
    }
    @Override
    public List<Fuel> findAll() {
        return fuelRepository.findAll();
    }
    @Override
    public Fuel findById(Long id) {
        return verifyIfExistTypeWithId(id);
    }
    @Override
    public MessageDto delete(Long id) {
        findById(id);
        fuelRepository.deleteById(id);
        return MsgStandard.msgStandardOk("deleted");
    }
    private Fuel verifyIfExistTypeWithId(Long id) {
        return fuelRepository.findById(id).
                orElseThrow(()->new NotFoundException("userType with this name not exist"));
    }

    @Override
    public Fuel put(Fuel dto) {
        Fuel userType = findById(dto.getId());
        verifyIfExistOtherTypeWithName(dto,userType.getName());
        return fuelRepository.save(userType);    }
    
    private void verifyIfExistOtherTypeWithName(Fuel type,String nameDb) {
        if (!type.getName().equals(nameDb)){
            verifyIfNotExistTypeWithName(type.getName());
            return;
        }else {
            type.setName(nameDb);
        }
    }

    private Boolean verifyIfNotExistTypeWithName(String name) {
        var optionalFuel = fuelRepository.findByName(name);
        if (optionalFuel.isPresent()){
            throw new BusinessException("userType whit this name already exist");
        }
        return true;
    }
}