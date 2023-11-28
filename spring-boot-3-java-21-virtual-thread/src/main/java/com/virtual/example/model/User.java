package com.virtual.example.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
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
