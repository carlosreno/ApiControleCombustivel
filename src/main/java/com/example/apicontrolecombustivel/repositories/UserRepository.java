package com.example.apicontrolecombustivel.repositories;

import com.example.apicontrolecombustivel.model.jpa.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
