package com.asheeque.springboot.ToDo.service;

import com.asheeque.springboot.ToDo.model.Status;
import com.asheeque.springboot.ToDo.model.SubTask;
import com.asheeque.springboot.ToDo.model.Task;
//import com.asheeque.springboot.ToDo.repository.TaskRepository;
import com.asheeque.springboot.ToDo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task addTask(Task newTask) {
        return taskRepository.save(newTask);
    }

    public ResponseEntity<Map<String, String>> deleteTask(Long taskId){
        taskRepository.deleteById(taskId);
        Map<String, String> response = new HashMap<>();
        response.put("status", "deleted");

        return ResponseEntity.ok(response);
    }

    public Map<String, Object> getAllTasksByUserId(Long userId) {
        List<Task> tasks =  taskRepository.findAllTasksByUserId(userId);
        List<Map<String,Object>> tasksMapList = new ArrayList<>();

        for(Task task:tasks){
            Map<String,Object> taskMap = new LinkedHashMap<>();
            taskMap.put("task_id",task.getId());
            taskMap.put("task_name",task.getTask_name());
            taskMap.put("due_date", task.getDue_date());
            taskMap.put("priority", task.getPriority());
            taskMap.put("category", task.getCategory());
            taskMap.put("status", task.getStatus());
            taskMap.put("color", task.getColor());

            List<Map<String, Object>> subtasksMapList = new ArrayList<>();
            for (SubTask subtask : task.getSubTasks()) {
                Map<String, Object> subtaskMap = new LinkedHashMap<>();
                subtaskMap.put("subtask_id", subtask.getId());
                subtaskMap.put("subtask_name", subtask.getName());
                subtaskMap.put("status", subtask.isStatus());
                subtasksMapList.add(subtaskMap);
            }
            taskMap.put("subtasks", subtasksMapList);
            tasksMapList.add(taskMap);


        }
        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("tasks", tasksMapList);
        return resultMap;
    }


    public int updateTaskStatus(Long taskId, Status status,Long userId) {
        return taskRepository.updateTaskStatus(taskId, status,userId);
    }

}
