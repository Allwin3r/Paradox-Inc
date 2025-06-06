package com.example.paradox.repository;

import com.example.paradox.entity.Task;
import com.example.paradox.entity.TaskStatus;
import com.example.paradox.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByUser(User user);
    List<Task> findByUserAndStatus(User user, TaskStatus status);
}