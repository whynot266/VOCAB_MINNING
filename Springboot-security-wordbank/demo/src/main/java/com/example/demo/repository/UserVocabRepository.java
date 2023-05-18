package com.example.demo.repository;

import com.example.demo.entities.UserEntity;
import com.example.demo.entities.UserVocabEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserVocabRepository extends JpaRepository<UserVocabEntity, Long> {
    @Query(value = "select count(id) from user_vocab where user_id=?1", nativeQuery = true)
    int bankCount(Long userId);
    @Query(value = "select vocab from user_vocab where user_id=?1", nativeQuery = true)
    List<String> bankList(Long userId);
    @Query(value = "select vocab from user_vocab where vocab=?1 and user_id=?2", nativeQuery = true)
    List<String> checkExistence(String word, Long userId);

    @Query(value = "select * from user_vocab where vocab=?1", nativeQuery = true)
    UserVocabEntity getUserVocab(String vocab);
    @Query(value = "select * from user_vocab where user_id=?1 and vocab_status='INBANK' and remember_times/learning_times<=0.8 order by RAND() limit 10", nativeQuery = true)
    List<UserVocabEntity> listToReview(Long userId);

    @Query(value = "select count(id) from user_vocab where remember_times/learning_times>=0.8", nativeQuery = true)
    int getProgress(Long userId);

}
