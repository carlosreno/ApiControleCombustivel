package com.example.apicontrolecombustivel.repositories;

import com.example.apicontrolecombustivel.model.jpa.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long> {
    Optional<UserType> findByName(String name);
}
