package com.example.paradox.service;

import com.example.paradox.config.SecurityUtils;
import com.example.paradox.dto.TaskDto;
import com.example.paradox.entity.Task;
import com.example.paradox.entity.TaskStatus;
import com.example.paradox.entity.User;
import com.example.paradox.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskDto createTask(TaskDto dto) {
        Task task = Task.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .status(dto.getStatus() != null ? dto.getStatus() : TaskStatus.TODO)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .user(SecurityUtils.getCurrentUser())
                .build();

        return toDto(taskRepository.save(task));
    }

    public List<TaskDto> getTasks(TaskStatus status) {
        User currentUser = SecurityUtils.getCurrentUser();
        List<Task> tasks = (status == null)
                ? taskRepository.findByUser(currentUser)
                : taskRepository.findByUserAndStatus(currentUser, status);

        return tasks.stream().map(this::toDto).collect(Collectors.toList());
    }

    public TaskDto updateTask(Integer id, TaskDto dto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getUser().getId().equals(SecurityUtils.getCurrentUser().getId())) {
            throw new RuntimeException("Access denied");
        }

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setUpdatedAt(LocalDateTime.now());

        return toDto(taskRepository.save(task));
    }

    public void deleteTask(Integer id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getUser().getId().equals(SecurityUtils.getCurrentUser().getId())) {
            throw new RuntimeException("Access denied");
        }

        taskRepository.delete(task);
    }

    private TaskDto toDto(Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .build();
    }
}