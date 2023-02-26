package com.asheeque.springboot.ToDo.controller;

import com.asheeque.springboot.ToDo.model.User;
import com.asheeque.springboot.ToDo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

//    @GetMapping("/{id}")
//    public User getUserById(@PathVariable Long id) {
//        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
//    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
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
