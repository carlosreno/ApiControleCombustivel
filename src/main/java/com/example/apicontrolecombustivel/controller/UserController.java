package com.example.apicontrolecombustivel.controller;

import com.example.apicontrolecombustivel.dto.MessageDto;
import com.example.apicontrolecombustivel.dto.model.UserDto;
import com.example.apicontrolecombustivel.model.jpa.Users;
import com.example.apicontrolecombustivel.service.SectorService;
import com.example.apicontrolecombustivel.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sector")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Users> create(@Valid @RequestBody UserDto dto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.create(dto));
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<Users>> findAll(){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.findAll());
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<Users> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.findById(id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageDto> delete(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.delete(id));
    }
    @PutMapping("/put/{id}")
    public ResponseEntity<Users> put(@PathVariable Long id,@Valid @RequestBody UserDto dto){
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.put(id,dto));
    }
    @PatchMapping("/patch/{id}")
    public ResponseEntity<Users> patch(@PathVariable Long id,@RequestBody UserDto dto){
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.patch(id,dto));
    }
}
