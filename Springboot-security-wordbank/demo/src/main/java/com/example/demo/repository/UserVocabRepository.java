package com.example.demo.repository;

import com.example.demo.entities.UserEntity;
import com.example.demo.entities.UserVocabEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserVocabRepository extends JpaRepository<UserVocabEntity, Long> {
    @Query(value = "select count(id) from user_vocab where user_id=?1", nativeQuery = true)
    int bankCount(Long userId);
}
