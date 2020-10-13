package com.assignment.apigateway.models;

public class User {

    private String firstName;
    private String lastName;
    private int birthYear;
    private String id;

    public User(String firstName, String lastName, int birthYear, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.id = id;
    }

    public User() {
    }

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
