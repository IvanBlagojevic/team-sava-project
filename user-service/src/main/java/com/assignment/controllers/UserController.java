package com.assignment.controllers;

import com.assignment.service.UserService;
import com.assignment.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/getById")
    public User getUserById(@RequestParam String id) {
        return userService.getUserById(id);
    }

    @GetMapping("/getAll")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/save")
    public void addNewUser(@RequestBody User user) {
        userService.save(user);
    }


}