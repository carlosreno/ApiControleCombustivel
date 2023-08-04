package com.example.apicontrolecombustivel.service;

import com.example.apicontrolecombustivel.dto.MessageDto;
import com.example.apicontrolecombustivel.dto.model.UserDto;
import org.apache.catalina.User;

import java.util.List;

public interface UserService {
    UserDto create(UserDto dto);
    List<User> findAll();
    MessageDto delete(Long id);
    User findById(Long id);
    User put(Long id, UserDto dto);
    User patch(Long id,UserDto dto);
}
