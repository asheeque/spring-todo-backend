package com.asheeque.springboot.ToDo.service;

import com.asheeque.springboot.ToDo.model.SubTask;
import com.asheeque.springboot.ToDo.repository.SubTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class SubTaskService {

    @Autowired
    private SubTaskRepository subTaskRepository;


    public SubTask saveSubTask(SubTask subTask) {
        return subTaskRepository.save(subTask);
    }

    public List<SubTask> saveAllSubTasks(List<SubTask> subTasks) {
        return subTaskRepository.saveAll(subTasks);
    }

    public SubTask updateStatus(Long subtaskId, boolean newStatus) {
        SubTask subTask = subTaskRepository.findById(subtaskId)
                .orElseThrow(() -> new RuntimeException("Subtask not found with id: " + subtaskId));

        subTask.setStatus(newStatus);
        return subTaskRepository.save(subTask);
    }

    public ResponseEntity<Map<String,String>> deleteSubtask(Long subtaskId){
        subTaskRepository.deleteById(subtaskId);
        Map<String, String> response = new HashMap<>();
        response.put("status", "deleted");

        return ResponseEntity.ok(response);
    }
}
