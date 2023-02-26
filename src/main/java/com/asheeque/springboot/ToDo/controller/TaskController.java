package com.asheeque.springboot.ToDo.controller;

import com.asheeque.springboot.ToDo.model.Task;
import com.asheeque.springboot.ToDo.model.User;
import com.asheeque.springboot.ToDo.service.TaskService;
import com.asheeque.springboot.ToDo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {

    @Autowired
    private TaskService taskService;


    @Autowired
    private UserServiceImpl userService;

    // Endpoint for creating a new task for a user
    @PostMapping("/{userId}/tasks")
    public Task createTask(@PathVariable long userId, @RequestBody Task newTask) {

        User user = userService.getUserById(userId);
//
        newTask.setUser(user);
        Task savedTask = taskService.addTask(newTask);
        return savedTask;
    }

    // Endpoint for getting all tasks of a user
    @GetMapping("/{userId}/tasks")
    public Map<String,Object> getAllTasksForUser(@PathVariable Long userId) {
        return taskService.getAllTasksByUserId(userId);
    }

}
