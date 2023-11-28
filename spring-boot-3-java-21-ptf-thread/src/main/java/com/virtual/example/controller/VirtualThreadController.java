package com.virtual.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.virtual.example.model.User;
import com.virtual.example.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/api")
@Slf4j
public class VirtualThreadController {

    private final UserRepository userRepository;

    @Autowired
    public VirtualThreadController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User not found with id " + id));
    }

    @GetMapping("/create")
    public User createRandomUser() {

        User userCreated =  userRepository.save(User.createRandomUser());

        var threadName = Thread.currentThread().toString();
        log.info("User created: {} on thread: {}", userCreated, threadName);
        return userCreated;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        User userCreated =  userRepository.save(user);

        var threadName = Thread.currentThread().toString();
        log.info("User created: {} on thread: {}", userCreated, threadName);
        return userCreated;
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User userDetails) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User not found with id " + id));

        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());

        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
        return "User deleted successfully";
    }
}