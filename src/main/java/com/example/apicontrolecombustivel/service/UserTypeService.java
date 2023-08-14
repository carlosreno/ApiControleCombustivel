package com.example.apicontrolecombustivel.service;

import com.example.apicontrolecombustivel.dto.MessageDto;
import com.example.apicontrolecombustivel.model.jpa.UserType;

import java.util.List;

public interface UserTypeService {
    UserType create(UserType dto);
    List<UserType> findAll();
    MessageDto delete(Long id);
    UserType findById(Long id);
    UserType put(UserType dto);
}
