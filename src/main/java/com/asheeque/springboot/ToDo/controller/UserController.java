package com.asheeque.springboot.ToDo.controller;

import com.asheeque.springboot.ToDo.dto.request.SigninRequest;
import com.asheeque.springboot.ToDo.dto.request.SignupRequest;
import com.asheeque.springboot.ToDo.dto.response.JwtResponse;
import com.asheeque.springboot.ToDo.model.User;
import com.asheeque.springboot.ToDo.repository.UserRepository;
import com.asheeque.springboot.ToDo.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/")
//@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;
    @GetMapping("users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @PostMapping("users")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }


    @PostMapping("users/signup")
    public ResponseEntity<User> registerUser(@Valid @RequestBody SignupRequest signupRequest){

        User currentUser = userServiceImpl.registerUser((signupRequest));

        User response ;
//        response.put("status", "deleted");
        response = currentUser;
        return ResponseEntity.ok(response);
    }


    @PostMapping("users/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody SigninRequest signinRequest){


        JwtResponse jwtToken = userServiceImpl.loginUser(signinRequest);

        return ResponseEntity.ok(jwtToken);
    }


}
