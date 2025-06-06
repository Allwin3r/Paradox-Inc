package com.example.paradox.controller;

import com.example.paradox.dto.TaskDto;
import com.example.paradox.entity.TaskStatus;
import com.example.paradox.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskPageController {
    private final TaskService taskService;

    @PostMapping
    public TaskDto createTask(@RequestBody TaskDto dto) {
        return taskService.createTask(dto);
    }

    @GetMapping
    public List<TaskDto> getTasks(@RequestParam(required = false) TaskStatus status) {
        return taskService.getTasks(status);
    }

    @PutMapping("/{id}")
    public TaskDto updateTask(@PathVariable Integer id, @RequestBody TaskDto dto) {
        return taskService.updateTask(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Integer id) {
        taskService.deleteTask(id);
    }
}
