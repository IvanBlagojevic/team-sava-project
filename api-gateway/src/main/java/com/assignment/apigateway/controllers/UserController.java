package com.assignment.apigateway.controllers;


import com.assignment.apigateway.clients.UserClient;
import com.assignment.apigateway.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class UserController {

    private UserClient userClient;

    @Autowired
    public UserController(UserClient userClient) {
        this.userClient = userClient;
    }

    @GetMapping("/getUserById")
    public User getUserById(@RequestParam String id) {
        User user = userClient.getUserById(id);

        if (Objects.isNull(user)) {
            throw new RuntimeException("There is no user with id=" + id);
        }

        return user;
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        List<User> allUsers = userClient.getAllUsers();

        if (CollectionUtils.isEmpty(allUsers)) {
            throw new RuntimeException("There are no users in database.");
        }

        return allUsers;
    }

    @PostMapping("/addNewUser")
    public void addNewUser(@RequestBody User user) {
        userClient.addNewUser(user);
    }


}
