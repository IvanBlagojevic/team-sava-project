package com.assignment.comparators;

import com.assignment.model.User;

import java.util.Comparator;

public class UserComparator implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        return Integer.compare(o2.getBirthYear(), o1.getBirthYear());
    }
}
