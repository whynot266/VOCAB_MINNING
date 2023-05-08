package com.example.demo.service;

import com.example.demo.entities.RoleEntity;
import com.example.demo.model.RoleName;

import java.util.Optional;

public interface RoleService{
    Optional<RoleEntity> findByName(RoleName name);
}
