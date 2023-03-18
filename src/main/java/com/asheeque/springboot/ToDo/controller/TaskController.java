package com.asheeque.springboot.ToDo.controller;

import com.asheeque.springboot.ToDo.model.Status;
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


    // Endpoint for creating a new task for a user
    @PostMapping("/tasks")
    public ResponseEntity<?> createTask( @RequestBody Task newTask) {
        Long userId = ControllerUtils.getAuthenticatedUserId();
        User user = userService.getUserById(userId);
//
        newTask.setUser(user);
        Task savedTask = taskService.addTask(newTask);
        Map<String, Task> response = new HashMap<>();
        System.out.println(savedTask.getId());
        response.put("Task", savedTask);
        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // Endpoint for getting all tasks of a user
    @GetMapping("/tasks")
    public ResponseEntity<?> getAllTasksForUser() {

        Long userId = ControllerUtils.getAuthenticatedUserId();
        System.out.println((userId));
        Map<String,Object> allTasks = taskService.getAllTasksByUserId(userId);
        return  ResponseEntity.status(HttpStatus.OK).body(allTasks);

    }

    @DeleteMapping("/tasks/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<String ,String >> deleteTask(@PathVariable Long taskId){
        return taskService.deleteTask(taskId);
    }

    @PutMapping("/tasks/{taskId}/status")
    public ResponseEntity<?> updateTaskStatus(@PathVariable Long taskId, @RequestBody Map<String, String> requestBody) {
        Long userId = ControllerUtils.getAuthenticatedUserId();
        Status newStatus;
        try {
            newStatus = Status.valueOf(requestBody.get("status"));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        int updatedRows = taskService.updateTaskStatus(taskId, newStatus, userId);
        Map<String, Integer> response = new HashMap<>();
        if (updatedRows > 0) {
            response.put("status", 1);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("status", 0);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

}
