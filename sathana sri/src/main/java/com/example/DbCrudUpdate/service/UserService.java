package com.example.DbCrudUpdate.service;

import com.example.DbCrudUpdate.model.Users;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<Users> getAllUsers();
    Optional<Users> getUserById(int id);
    Users saveUser(Users user);
    void deleteUser(int id);
}
