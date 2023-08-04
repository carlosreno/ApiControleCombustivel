package com.example.apicontrolecombustivel.service.impl;

import com.example.apicontrolecombustivel.dto.MessageDto;
import com.example.apicontrolecombustivel.dto.model.UserDto;
import org.apache.catalina.UserDto;
import com.example.apicontrolecombustivel.exception.NotFoundException;
import org.apache.catalina.UserMapper;
import org.apache.catalina.User;
import org.apache.catalina.UserRepository;
import org.apache.catalina.UserService;
import com.example.apicontrolecombustivel.service.UserService;
import com.example.apicontrolecombustivel.utils.MsgStandard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository companyRepository;

    @Override
    public User create(UserDto dto) {
        User company = UserMapper.fromDtoToEntity(null,dto);
        return companyRepository.save(company);
    }

    @Override
    public List<User> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public MessageDto delete(Long id) {
        verifyIfExistAndReturn(id);
        companyRepository.deleteById(id);
        return MsgStandard.msgStandardOk("deleted");
    }

    @Override
    public User findById(Long id) {
        return verifyIfExistAndReturn(id);
    }

    private User verifyIfExistAndReturn(Long id) {
        return companyRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("User with this id not exist")
        );
    }

    @Override
    public User put(Long id, UserDtoo dto) {
        findById(id);
        User company = UserMapper.fromDtoToEntity(id,dto);
        return companyRepository.save(company);
    }

    @Override
    public User patch(Long id, UserDto dto) {
        var companyDb = findById(id);
        User company = User.builder()
                .id(id)
                .cnpj(dto.cnpj() != null ? dto.cnpj() : companyDb.getCnpj())
                .build();
        return companyRepository.save(company);
    }
}
