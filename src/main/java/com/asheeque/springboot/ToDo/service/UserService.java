package com.asheeque.springboot.ToDo.service;

import com.asheeque.springboot.ToDo.dto.request.SigninRequest;
import com.asheeque.springboot.ToDo.dto.request.SignupRequest;
import com.asheeque.springboot.ToDo.dto.response.JwtResponse;
import com.asheeque.springboot.ToDo.model.User;

public interface UserService {
    User getUserById(Long id);
    User createUser(User user);


    User registerUser(SignupRequest signupRequest);

    JwtResponse loginUser(SigninRequest signinRequest);

}
