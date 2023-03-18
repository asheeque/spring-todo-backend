package com.asheeque.springboot.ToDo.service;

import com.asheeque.springboot.ToDo.model.SubTask;
import com.asheeque.springboot.ToDo.repository.SubTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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
}
