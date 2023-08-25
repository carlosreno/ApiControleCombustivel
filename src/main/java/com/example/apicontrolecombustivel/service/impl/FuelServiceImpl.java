package com.example.apicontrolecombustivel.service.impl;

import com.example.apicontrolecombustivel.dto.MessageDto;
import com.example.apicontrolecombustivel.exception.BusinessException;
import com.example.apicontrolecombustivel.exception.NotFoundException;
import com.example.apicontrolecombustivel.model.jpa.Fuels;
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
    public Fuels create(Fuels dto) {
        verifyIfNotExistTypeWithName(dto.getName());
        return fuelRepository.save(dto);
    }
    @Override
    public List<Fuels> findAll() {
        return fuelRepository.findAll();
    }
    @Override
    public Fuels findById(Long id) {
        return verifyIfExistTypeWithId(id);
    }
    @Override
    public MessageDto delete(Long id) {
        findById(id);
        fuelRepository.deleteById(id);
        return MsgStandard.msgStandardOk("deleted");
    }
    private Fuels verifyIfExistTypeWithId(Long id) {
        return fuelRepository.findById(id).
                orElseThrow(()->new NotFoundException("fuel with this id not exist"));
    }

    @Override
    public Fuels put(Fuels dto) {
        Fuels userType = findById(dto.getId());
        verifyIfExistOtherTypeWithName(dto,userType.getName());
        return fuelRepository.save(userType);    }
    
    private void verifyIfExistOtherTypeWithName(Fuels type, String nameDb) {
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
            throw new BusinessException("fuel whit this name already exist");
        }
        return true;
    }
}