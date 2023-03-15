package com.asheeque.springboot.ToDo.controller;

import com.asheeque.springboot.ToDo.model.Task;
import com.asheeque.springboot.ToDo.model.User;
import com.asheeque.springboot.ToDo.security.services.UserDetailsImpl;
import com.asheeque.springboot.ToDo.service.TaskService;
import com.asheeque.springboot.ToDo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
//@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
public class TaskController {

    @Autowired
    private TaskService taskService;


    @Autowired
    private UserServiceImpl userService;

    private Long getAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return userDetails.getId();
    }

    // Endpoint for creating a new task for a user
    @PostMapping("/tasks")
    public ResponseEntity<?> createTask( @RequestBody Task newTask) {
        Long userId = getAuthenticatedUserId();
        User user = userService.getUserById(userId);
//
        newTask.setUser(user);
        Task savedTask = taskService.addTask(newTask);
        Map<String, Task> response = new HashMap<>();
        response.put("Task", savedTask);
        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // Endpoint for getting all tasks of a user
    @GetMapping("/tasks")
    public ResponseEntity<?> getAllTasksForUser() {

        Long userId = getAuthenticatedUserId();
        System.out.println((userId));
        Map<String,Object> allTasks = taskService.getAllTasksByUserId(userId);
        return  ResponseEntity.status(HttpStatus.OK).body(allTasks);

    }


    @DeleteMapping("/{userId}/tasks/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<String ,String >> deleteTask(@PathVariable Long userId,@PathVariable Long taskId){
        return taskService.deleteTask(taskId);
    }
}
