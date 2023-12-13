package ru.eosreign.taskmanagementsystem.service;

import org.springframework.stereotype.Service;
import ru.eosreign.taskmanagementsystem.dao.TaskDao;
import ru.eosreign.taskmanagementsystem.dto.ListTaskDto;
import ru.eosreign.taskmanagementsystem.dto.NewTaskDto;
import ru.eosreign.taskmanagementsystem.dto.TaskDto;
import ru.eosreign.taskmanagementsystem.dto.UpdateTaskDto;
import ru.eosreign.taskmanagementsystem.exception.*;
import ru.eosreign.taskmanagementsystem.mapper.NewTaskDtoMapper;
import ru.eosreign.taskmanagementsystem.mapper.UpdateTaskDtoMapper;

@Service
public class TaskService {
    private final TaskDao taskDao;
    public TaskService(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public TaskDto createTask(NewTaskDto dto) {
        try {
            if (!statusIsValid(dto.getStatus())) {
                throw new InvalidTaskStatusException("Status must be in high register and " +
                        "can be in variants: IN_PROCESS, AWAITING, COMPLETE");
            } else if (!priorityIsValid(dto.getPriority())) {
                throw new InvalidTaskPriorityException("Priority must be in high register and " +
                        "can be in variants: HIGH, MEDIUM, LOW");
            }
            TaskDto response = NewTaskDtoMapper.toTaskDto(dto);
            response.setId(taskDao.createTask(dto)
                    .orElseThrow(() -> new CreatingTaskIsFailedException("Creating task is failed")
                    ));
            return response;

        } catch (InvalidTaskStatusException e) {
            throw new InvalidTaskStatusException(e.getMessage());
        } catch (InvalidTaskPriorityException e) {
            throw new InvalidTaskPriorityException(e.getMessage());
        } catch (CreatingTaskIsFailedException e) {
            throw new CreatingTaskIsFailedException(e.getMessage());
        }
    }

    public TaskDto getTask(Long id) throws TaskNotFoundException {
        return taskDao.getTask(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found by id" + id));
    }

    public ListTaskDto getTasks() throws TaskTableIsEmptyException {
        return new ListTaskDto(taskDao.getTasks()
                .orElseThrow(() -> new TaskTableIsEmptyException("Task table is empty")));
    }

    public TaskDto updateTask(UpdateTaskDto dto, Long id) throws TaskNotFoundException {
        taskDao.getTask(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found by id" + id)
                );
        TaskDto response = UpdateTaskDtoMapper.toTaskDto(dto);
        response.setId(id);
        taskDao.updateTask(dto, id);
        return response;
    }

    public TaskDto deleteTask(Long id) throws TaskNotFoundException {
        TaskDto dto = taskDao.getTask(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found by id" + id));
        taskDao.deleteTask(id);
        return dto;
    }

    private boolean statusIsValid(String status) {
        return status.equals("IN_PROCESS") || status.equals("AWAITING") || status.equals("COMPLETE");
    }

    private boolean priorityIsValid(String priority) {
        return priority.equals("HIGH") || priority.equals("MEDIUM") || priority.equals("LOW");
    }
}
