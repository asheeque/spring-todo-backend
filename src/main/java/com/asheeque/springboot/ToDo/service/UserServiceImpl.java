package com.asheeque.springboot.ToDo.service;

import com.asheeque.springboot.ToDo.dto.request.SignupRequest;
import com.asheeque.springboot.ToDo.model.User;
import com.asheeque.springboot.ToDo.repository.UserRepository;
import com.asheeque.springboot.ToDo.security.PasswordConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{


    @Autowired
    private PasswordConfig passwordConfig;
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User registerUser(SignupRequest signupRequest) {

        PasswordEncoder passwordEncoder = passwordConfig.passwordEncoder();
        User newUser = new User(signupRequest.getUsername(), signupRequest.getEmail(), passwordEncoder.encode(signupRequest.getPassword()));

        return  userRepository.save(newUser);
//        return newUser;
    }
}
