package ru.eosreign.taskmanagementsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.eosreign.taskmanagementsystem.dto.ListTaskDto;
import ru.eosreign.taskmanagementsystem.dto.TaskDto;
import ru.eosreign.taskmanagementsystem.service.TaskService;


@RestController
@RequestMapping("/api/task")
public class TaskController {
    public TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping()
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto dto) {
        return new ResponseEntity<>(taskService.createTask(dto), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> readTask(@PathVariable("id") Long id) {
        return new ResponseEntity<>(taskService.getTask(id), HttpStatus.FOUND);
    }

    @GetMapping()
    public ResponseEntity<ListTaskDto> readTasks() {
        return new ResponseEntity<>(taskService.getTasks(), HttpStatus.FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto dto, @PathVariable("id") Long id) {
        return new ResponseEntity<>(taskService.updateTask(dto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
