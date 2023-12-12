package ru.eosreign.taskmanagementsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.eosreign.taskmanagementsystem.dto.ListTaskDto;
import ru.eosreign.taskmanagementsystem.dto.NewTaskDto;
import ru.eosreign.taskmanagementsystem.dto.TaskDto;
import ru.eosreign.taskmanagementsystem.entity.Customer;
import ru.eosreign.taskmanagementsystem.service.TaskService;

@Tag(name = "tasks", description = "The task API")
@RestController
@RequestMapping("/api/task")
public class TaskController {
    public TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(summary = "Create task",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Task is created",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Customer.class)
                                    )
                            }
                    )
            })
    @PostMapping()
    public ResponseEntity<TaskDto> createTask(@RequestBody NewTaskDto dto) {
        return new ResponseEntity<>(taskService.createTask(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Get task by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Found task",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Customer.class)
                                    )
                            }
                    )
            })
    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> readTask(@PathVariable("id") Long id) {
        return new ResponseEntity<>(taskService.getTask(id), HttpStatus.FOUND);
    }

    @Operation(summary = "Get tasks",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Found tasks",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = Customer.class))
                                    )
                            }
                    )
            })
    @GetMapping()
    public ResponseEntity<ListTaskDto> readTasks() {
        return new ResponseEntity<>(taskService.getTasks(), HttpStatus.FOUND);
    }

    @Operation(summary = "Update task by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Task is updated",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Customer.class)
                                    )
                            }
                    )
            })
    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto dto,
                                              @PathVariable("id") Long id) {
        return new ResponseEntity<>(taskService.updateTask(dto, id), HttpStatus.OK);
    }

    @Operation(summary = "Delete task by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Task is deleted",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Customer.class)
                                    )
                            }
                    )

            })
    @DeleteMapping("/{id}")
    public ResponseEntity<TaskDto> deleteTask(@PathVariable("id") Long id) {
        return new ResponseEntity<>(taskService.deleteTask(id), HttpStatus.OK);
    }
}
