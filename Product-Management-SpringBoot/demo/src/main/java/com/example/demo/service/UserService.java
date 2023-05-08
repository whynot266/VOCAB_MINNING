package com.example.demo.service;

import com.example.demo.entities.UserEntity;

import java.util.Optional;

public interface UserService {
    Optional<UserEntity> findByEmail(String email);
    boolean existByEmail(String email);

    UserEntity save(UserEntity user);
}
