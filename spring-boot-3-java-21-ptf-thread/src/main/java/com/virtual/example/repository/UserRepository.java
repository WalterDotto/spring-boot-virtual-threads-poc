package com.virtual.example.repository;

import org.springframework.data.repository.ListCrudRepository;
import com.virtual.example.model.User;

public interface UserRepository extends ListCrudRepository<User, Integer> {

}