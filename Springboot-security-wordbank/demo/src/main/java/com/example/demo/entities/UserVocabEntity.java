/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.entities;


import com.example.demo.enums.VocabStatus;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_vocab")
public class UserVocabEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @Column(name = "vocab")
    private String vocab;
    @Column(name = "learning_times")
    private int learningTimes;
    @Column(name="remember_times")
    private int rememberTimes;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VocabStatus vocabStatus;

    public UserVocabEntity() {
    }

    public UserVocabEntity(UserEntity user, String vocab, int learningTimes, int rememberTimes, VocabStatus vocabStatus) {
        this.user = user;
        this.vocab = vocab;
        this.learningTimes = learningTimes;
        this.rememberTimes = rememberTimes;
        this.vocabStatus = vocabStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setAccount(UserEntity account) {
        this.user = user;
    }

    public String getVocab() {
        return vocab;
    }

    public void setVocab(String vocab) {
        this.vocab = vocab;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public int getLearningTimes() {
        return learningTimes;
    }

    public void setLearningTimes(int learningTimes) {
        this.learningTimes = learningTimes;
    }

    public int getRememberTimes() {
        return rememberTimes;
    }

    public void setRememberTimes(int rememberTimes) {
        this.rememberTimes = rememberTimes;
    }

    public VocabStatus getVocabStatus() {
        return vocabStatus;
    }

    public void setVocabStatus(VocabStatus vocabStatus) {
        this.vocabStatus = vocabStatus;
    }
}
