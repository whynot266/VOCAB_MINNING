package com.example.demo.service.impl;

import com.example.demo.entities.RoleEntity;
import com.example.demo.model.RoleName;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Override
    public Optional<RoleEntity> findByName(RoleName name) {
        return roleRepository.findByName(name);
    }
}
