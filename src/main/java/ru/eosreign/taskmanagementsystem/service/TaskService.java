package ru.eosreign.taskmanagementsystem.service;

import org.springframework.stereotype.Service;
import ru.eosreign.taskmanagementsystem.dao.TaskDao;
import ru.eosreign.taskmanagementsystem.dto.ListTaskDto;
import ru.eosreign.taskmanagementsystem.dto.TaskDto;

@Service
public class TaskService {
    private final TaskDao taskDao;
    public TaskService(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public TaskDto createTask(TaskDto dto) {
        taskDao.createTask(dto);
        return dto;
    }

    public TaskDto getTask(Long id) {
        return taskDao.getTask(id);
    }

    public ListTaskDto getTasks() {
        return new ListTaskDto(taskDao.getTasks());
    }

    public TaskDto updateTask(TaskDto dto, Long id) {
        taskDao.updateTask(dto, id);
        return dto;
    }

    public void deleteTask(Long id) {
        taskDao.deleteTask(id);
    }
}
