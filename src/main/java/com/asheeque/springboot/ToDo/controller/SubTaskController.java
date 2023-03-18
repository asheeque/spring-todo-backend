package com.asheeque.springboot.ToDo.controller;


import com.asheeque.springboot.ToDo.dto.request.UpdateSubTaskStatusRequest;
import com.asheeque.springboot.ToDo.model.SubTask;
import com.asheeque.springboot.ToDo.security.services.UserDetailsImpl;
import com.asheeque.springboot.ToDo.service.SubTaskService;
import com.asheeque.springboot.ToDo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/subtasks")
public class SubTaskController {

    @Autowired
    private SubTaskService subTaskService;

    @Autowired
    private UserServiceImpl userService;


    @PostMapping("/add")
    public ResponseEntity<Map<String, Objects>> addSubTasks(@RequestBody SubTask subTask) {
        Long userId = ControllerUtils.getAuthenticatedUserId();
        SubTask savedSubTasks = subTaskService.saveSubTask(subTask);

//        List<Map<String, Object>> response = savedSubTasks.stream().map(subTask -> {
        Map<String, Object> subTaskData = new HashMap<>();
//            System.out.println(subTask.getId(),subTask.getName());
        subTaskData.put("id", subTask.getId());
        subTaskData.put("subtask_name", subTask.getName());
        subTaskData.put("status", subTask.isStatus());
//        return subTaskData;
//        }).collect(Collectors.toList());

        return ResponseEntity.ok(subTaskData);

    }


    @PutMapping("/update-status")
    public ResponseEntity<String> updateSubTaskStatus(@RequestBody UpdateSubTaskStatusRequest updateRequest) {
        System.out.println(updateRequest.getSubtaskId());
        System.out.println(updateRequest.isNewStatus());
        SubTask updatedSubTask = subTaskService.updateStatus(updateRequest.getSubtaskId(),updateRequest.isNewStatus());
        System.out.println(updatedSubTask.getName());
        return ResponseEntity.ok("updatedSubTask");
    }
}
