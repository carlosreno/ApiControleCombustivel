package com.example.apicontrolecombustivel.service.impl;

import com.example.apicontrolecombustivel.dto.MessageDto;
import com.example.apicontrolecombustivel.exception.BusinessException;
import com.example.apicontrolecombustivel.exception.NotFoundException;
import com.example.apicontrolecombustivel.model.jpa.UserType;
import com.example.apicontrolecombustivel.repositories.UserTypeRepository;
import com.example.apicontrolecombustivel.service.UserTypeService;
import com.example.apicontrolecombustivel.utils.MsgStandard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UseTyperServiceImpl implements UserTypeService {
    private final UserTypeRepository userTypeRepository;

    @Override
    public UserType create(UserType dto) {
        verifyIfNotExistTypeWithName(dto.getName());
        return userTypeRepository.save(dto);
    }

    @Override
    public List<UserType> findAll() {
        return userTypeRepository.findAll();
    }

    @Override
    public UserType findById(Long id) {
        return verifyIfExistTypeWithId(id);
    }

    @Override
    public MessageDto delete(Long id) {
        findById(id);
        userTypeRepository.deleteById(id);
        return MsgStandard.msgStandardOk("deleted");
    }

    private UserType verifyIfExistTypeWithId(Long id) {
        return userTypeRepository.findById(id).
                orElseThrow(()->new NotFoundException("userType with this name not exist"));
    }

    @Override
    public UserType put(UserType dto) {
        UserType userType = findById(dto.getId());
        verifyIfExistOtherTypeWithName(dto,userType.getName());
        return userTypeRepository.save(userType);    }
    
    private void verifyIfExistOtherTypeWithName(UserType type,String nameDb) {
        if (!type.getName().equals(nameDb)){
            verifyIfNotExistTypeWithName(type.getName());
            return;
        }else {
            type.setName(nameDb);
        }
    }

    private Boolean verifyIfNotExistTypeWithName(String name) {
        var optionalUserType = userTypeRepository.findByName(name);
        if (optionalUserType.isPresent()){
            throw new BusinessException("userType whit this name already exist");
        }
        return true;
    }
}