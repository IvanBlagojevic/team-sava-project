package com.assignment.modelDb;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class User {
    private String firstName;
    private String lastName;
    private int birthYear;

    public User(String firstName, String lastName, int birthYear, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.id = id;
    }

    public User(String firstName, String lastName, int birthYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
    }

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    public User() {
    }

    @Id
    private String id;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}



