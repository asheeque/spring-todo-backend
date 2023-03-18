package com.asheeque.springboot.ToDo.repository;

import com.asheeque.springboot.ToDo.model.Status;
import com.asheeque.springboot.ToDo.model.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE t.user.user_id = :userId")
    List<Task> findAllTasksByUserId(@Param("userId") Long userId);


    @Transactional
    @Modifying
    @Query("UPDATE Task t SET t.status = :status WHERE t.id = :taskId AND t.user.id = :userId")
    int updateTaskStatus(@Param("taskId") Long taskId, @Param("status") Status status, @Param("userId") Long userId);

}
