package com.assignment.service.impl;

import com.assignment.comparators.UserComparator;
import com.assignment.modelDb.User;
import com.assignment.repository.UserRepository;
import com.assignment.service.SequenceGeneratorService;
import com.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository repository;

    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    public UserServiceImpl(UserRepository repository, SequenceGeneratorService sequenceGeneratorService) {
        this.repository = repository;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }


    @Override
    @Transactional
    public com.assignment.model.User getUserById(String id) {
        Optional<User> user = repository.findById(id);
        return user.map(this::fromEntityToDto).orElse(null);
    }

    @Override
    @Transactional
    public List<com.assignment.model.User> getAllUsers() {
        List<User> dbUsers = repository.findAll();

        if (CollectionUtils.isEmpty(dbUsers)) {
            return Collections.EMPTY_LIST;
        }

        return dbUsers.stream().map(this::fromEntityToDto).sorted(new UserComparator()).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void save(com.assignment.model.User user) {
        repository.save(fromDtoToEntity(user));
    }

    private com.assignment.model.User fromEntityToDto(User user) {
        return new com.assignment.model.User(user.getFirstName(), user.getLastName(), user.getBirthYear(), user.getId());
    }

    private User fromDtoToEntity(com.assignment.model.User user) {
        return new User(user.getFirstName(), user.getLastName(), user.getBirthYear(), String.valueOf(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME)));
    }

}

