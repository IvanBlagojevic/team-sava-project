package com.assignment.service;

import com.assignment.model.User;

import java.util.List;


public interface UserService {

    User getUserById(String id);

    List<User> getAllUsers();

    void save(User user);


}
