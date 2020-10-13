package com.assignment.apigateway.clients;

import com.assignment.apigateway.models.User;
import com.assignment.apigateway.models.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class UserClientImpl implements UserClient {


    private RestTemplate restTemplate;


    @Autowired
    public UserClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public User getUserById(String id) {

        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        User user = restTemplate.getForObject("http://user-service:8088//getById?id={id}", User.class,
                params);
        return !Objects.isNull(user) ? user : null;
    }

    @Override
    public List<User> getAllUsers() {
        ResponseEntity<User[]> responseEntity = restTemplate.getForEntity("http://user-service:8088//getAll", User[].class);
        User[] users = responseEntity.getBody();
        return !Objects.isNull(users) ? Arrays.asList(users) : null;
    }

    @Override
    public void addNewUser(User user) {
        restTemplate.postForObject("http://user-service:8088//save", user, User.class);
    }
}
