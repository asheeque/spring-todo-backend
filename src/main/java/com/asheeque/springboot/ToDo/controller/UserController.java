package com.asheeque.springboot.ToDo.controller;

import com.asheeque.springboot.ToDo.dto.request.SignupRequest;
import com.asheeque.springboot.ToDo.model.User;
import com.asheeque.springboot.ToDo.repository.UserRepository;
import com.asheeque.springboot.ToDo.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;
    @GetMapping("users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

//    @GetMapping("/{id}")
//    public User getUserById(@PathVariable Long id) {
//        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
//    }

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

//    @PutMapping("/{id}")
//    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
//        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
//        user.setEmail(userDetails.getEmail());
//        user.setPassword(userDetails.getPassword());
//        user.setTasks(userDetails.getTasks()); // assuming the User model has a "tasks" field representing a list of tasks
//        return userRepository.save(user);
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
//        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
//        userRepository.delete(user);
//        return ResponseEntity.ok().build();
//    }
}
