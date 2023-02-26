package com.asheeque.springboot.ToDo.model;
import jakarta.persistence.*;

@Entity
@Table(name = "sub_tasks")
public class SubTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subtask_id", nullable = false)
    private Long id;

    @Column(name = "subtask_name", nullable = false)
    private String name;

    @Column(name = "status", nullable = false)
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    // Constructors, getters, and setters

    public  SubTask(){}
    public SubTask(Long id, String name, boolean status, Task task) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.task = task;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
