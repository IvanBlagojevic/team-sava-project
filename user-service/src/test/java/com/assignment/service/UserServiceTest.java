package com.assignment.service;

import com.assignment.modelDb.User;
import com.assignment.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.runner.RunWith;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    private final String EXISTING_USER_ID = "1";
    private final String NON_EXISTING_USER_ID = "2";

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Before
    public void before() {
        when(getUserRepository().findById(EXISTING_USER_ID)).thenReturn(java.util.Optional.of(new User("John", "Dou", 1985)));
        when(getUserRepository().findById(NON_EXISTING_USER_ID)).thenReturn(java.util.Optional.empty());

        when(getUserRepository().findAll()).thenReturn(getUserList());
    }

    public UserService getUserService() {
        return userService;
    }

    private List<User> getUserList() {
        List<User> list = new ArrayList<>();
        list.add(new User("Jack", "Nicholson", 1937));
        list.add(new User("Quentin", "Tarantino", 1963));
        list.add(new User("Robert", "De Niro", 1943));
        return list;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Test
    public void getExistingUserByIdTest() {
        com.assignment.model.User user = getUserService().getUserById(EXISTING_USER_ID);
        assertFalse(Objects.isNull(user));
    }

    @Test
    public void getNonExistingUserByIdTest() {
        com.assignment.model.User user = getUserService().getUserById(NON_EXISTING_USER_ID);
        assertTrue(Objects.isNull(user));
    }

    @Test
    public void getAllUsersTest() throws JsonProcessingException {
        List<com.assignment.model.User> allUsers = getUserService().getAllUsers();
        assertTrue(allUsers.size() > 0);
        assertEquals(1963, allUsers.get(0).getBirthYear());
        assertEquals(1943, allUsers.get(1).getBirthYear());
        assertEquals(1937, allUsers.get(2).getBirthYear());
    }


}
