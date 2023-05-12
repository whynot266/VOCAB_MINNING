package com.example.demo.service.impl;

import com.example.demo.entities.UserEntity;
import com.example.demo.repository.IUserRepository;
import com.example.demo.repository.UserVocabRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    IUserRepository userRepository;
    @Autowired
    UserVocabRepository userVocabRepository;
    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    @Override
    public UserEntity save(UserEntity user){
        return userRepository.save(user);
    }
    public Object bankCount(UserEntity user){
        return userVocabRepository.bankCount(user.getId());
    }
}
