package com.example.demo.service.impl;

import com.example.demo.entities.UserEntity;
import com.example.demo.entities.UserVocabEntity;
import com.example.demo.repository.IUserRepository;
import com.example.demo.repository.UserVocabRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<String> bankList(UserEntity user){
        return userVocabRepository.bankList(user.getId());
    }
    public boolean checkExistence(String word, UserEntity user){
        if (userVocabRepository.checkExistence(word, user.getId()).size()==1){
            return true;
        }
        return false;
    }
    public List<UserVocabEntity> listToReview(UserEntity user){
        return userVocabRepository.listToReview(user.getId());
    }
    public UserVocabEntity getUserVocabByName(String vocab){
        return userVocabRepository.getUserVocab(vocab);

    }
    public int getProgress(UserEntity user){
        return (int)userVocabRepository.getProgress(user.getId())*100/ userVocabRepository.bankCount(user.getId());
    }
    public void saveVocab(UserVocabEntity userVocabEntity){
        userVocabRepository.save(userVocabEntity);
    }
}
