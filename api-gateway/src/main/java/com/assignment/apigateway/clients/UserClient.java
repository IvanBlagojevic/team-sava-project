package com.assignment.apigateway.clients;

import com.assignment.apigateway.models.User;

import java.util.List;

public interface UserClient {

    User getUserById(String id);

    List<User> getAllUsers();

    void addNewUser(User user);
}
