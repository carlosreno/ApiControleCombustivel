package com.example.apicontrolecombustivel.service;

import com.example.apicontrolecombustivel.dto.MessageDto;
import com.example.apicontrolecombustivel.dto.model.UserDto;
import com.example.apicontrolecombustivel.model.jpa.Users;

import java.util.List;

public interface UserService {
    Users create(UserDto dto);
    List<Users> findAll();
    MessageDto delete(Long id);
    Users findById(Long id);
    Users put(Long id, UserDto dto);
    Users patch(Long id,UserDto dto);
}
