package com.asheeque.springboot.ToDo.service;

import com.asheeque.springboot.ToDo.model.User;

public interface UserService {
    User getUserById(Long id);
    User createUser(User user);
}
