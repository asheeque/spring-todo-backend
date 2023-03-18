package com.asheeque.springboot.ToDo.repository;

import com.asheeque.springboot.ToDo.model.SubTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubTaskRepository extends JpaRepository<SubTask, Long> {
}
