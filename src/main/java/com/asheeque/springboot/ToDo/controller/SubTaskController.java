package com.asheeque.springboot.ToDo.controller;


import com.asheeque.springboot.ToDo.dto.request.UpdateSubTaskStatusRequest;
import com.asheeque.springboot.ToDo.model.SubTask;
import com.asheeque.springboot.ToDo.security.services.UserDetailsImpl;
import com.asheeque.springboot.ToDo.service.SubTaskService;
import com.asheeque.springboot.ToDo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/")
public class SubTaskController {

    @Autowired
    private SubTaskService subTaskService;

    @Autowired
    private UserServiceImpl userService;


    @PostMapping("subtasks")
    public ResponseEntity<Map<String, Object>> addSubTasks(@RequestBody SubTask subTask) {
        Long userId = ControllerUtils.getAuthenticatedUserId();
        SubTask savedSubTasks = subTaskService.saveSubTask(subTask);

        Map<String, Object> subTaskData = new HashMap<>();
//            System.out.println(subTask.getId(),subTask.getName());
        subTaskData.put("id", subTask.getId());
        subTaskData.put("subtask_name", subTask.getName());
        subTaskData.put("status", subTask.isStatus());

        return ResponseEntity.ok(subTaskData);

    }

    @DeleteMapping("subtasks/{subtaskId}")
    public ResponseEntity<Map<String,String>> deleteSubtask(@PathVariable Long subtaskId){
        return subTaskService.deleteSubtask(subtaskId);
    }




    @PutMapping("subtasks/update-status")
    public ResponseEntity<Map<String,Object>> updateSubTaskStatus(@RequestBody UpdateSubTaskStatusRequest updateRequest) {

        SubTask updatedSubTask = subTaskService.updateStatus(updateRequest.getSubtaskId(),updateRequest.isNewStatus());
        Map<String, Object> subTaskData = new HashMap<>();
        subTaskData.put("id", updatedSubTask.getId());
        subTaskData.put("status", "updated");
        return ResponseEntity.ok(subTaskData);
    }
}
