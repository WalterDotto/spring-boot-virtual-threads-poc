package com.virtual.example.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username;

    private String email;

    private String password;

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static User createRandomUser() {
        String randomUsername = "user" + UUID.randomUUID().toString().substring(0, 5);
        String randomEmail = randomUsername + "@example.com";
        String randomPassword = UUID.randomUUID().toString().substring(0, 8);
        return new User(randomUsername, randomEmail, randomPassword);
    }

    @Override
    public String toString() {
        return "{\"User\":{"
            + "\"id\":\"" + id + "\""
            + ",\"username\":\"" + username + "\""
            + ", \"email\":\"" + email + "\""
            + ", \"password\":\"" + password + "\""
            + "}}";
    }
}
