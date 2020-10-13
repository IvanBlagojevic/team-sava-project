package com.assignment.apigateway.controllers;

import com.assignment.apigateway.clients.UserClient;
import com.assignment.apigateway.models.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    public final String EXISTING_USER_ID = "1";
    public final String NON_EXISTING_USER_ID = "2";


    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserClient userClient;

    public MockMvc getMvc() {
        return mvc;
    }

    public UserClient getUserClient() {
        return userClient;
    }

    @Before
    public void before() {
        when(getUserClient().getUserById(EXISTING_USER_ID)).thenReturn(new User("John", "Doe", 1985, "1"));
        when(getUserClient().getUserById(NON_EXISTING_USER_ID)).thenReturn(null);

        when(getUserClient().getAllUsers()).thenReturn(null);
    }

    @Test
    public void getUserByIdReturnsExistingUserTest()
            throws Exception {

        mvc.perform(MockMvcRequestBuilders
                .get("/getUserById?id=1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"));
    }

    @Test
    public void getUserByIdReturnsNonExistingUserTest()
            throws Exception {

        mvc.perform(MockMvcRequestBuilders
                .get("/getUserById?id=2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("There is no user with id=2"));
    }

    @Test
    public void getAllUsersReturnsEmptyListTest()
            throws Exception {

        mvc.perform(MockMvcRequestBuilders
                .get("/getAllUsers")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("There are no users in database."));
    }

}
