package ru.eosreign.taskmanagementsystem.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.eosreign.taskmanagementsystem.dao.TaskDao;
import ru.eosreign.taskmanagementsystem.dto.ListTaskDto;
import ru.eosreign.taskmanagementsystem.dto.NewTaskDto;
import ru.eosreign.taskmanagementsystem.dto.TaskDto;
import ru.eosreign.taskmanagementsystem.dto.UpdateTaskDto;
import ru.eosreign.taskmanagementsystem.exception.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    public TaskDao taskDao;

    @InjectMocks
    public TaskService taskService;

    private TaskDto testTaskDto;

    private UpdateTaskDto testUpdateTaskDto;

    private NewTaskDto testNewTaskDto;

    @BeforeEach()
    public void init() {
        testTaskDto = methodReturnTestTaskDto();
        testNewTaskDto = methodReturnTestNewTaskDto();
        testUpdateTaskDto = methodReturnTestUpdateTaskDto();
    }

    @AfterEach
    public void teardown() {
        testTaskDto = null;
        testNewTaskDto = null;
        testUpdateTaskDto = null;
    }


    @Test
    public void createTask_Valid_NewTaskDto_ReturnTaskDto() {
        testTaskDto.setId(1L);
        testTaskDto.setExecutor(null);

        Mockito.when(taskDao.createTask(testNewTaskDto))
                .thenReturn(Optional.of(1L));

        assertThat(testTaskDto).isEqualTo(taskService.createTask(testNewTaskDto));
    }

    @Test
    public void createTask_Invalid_NewTaskDto_ByPriority_ExceptionThrown() {
        testNewTaskDto.setPriority("inProcess");

        assertThatThrownBy(() -> taskService.createTask(testNewTaskDto))
                .isInstanceOf(InvalidTaskPriorityException.class)
                .hasMessageContaining("Priority must be in high register and " +
                        "can be in variants: HIGH, MEDIUM, LOW");
    }

    @Test
    public void createTask_Invalid_NewTaskDto_ByStatus_ExceptionThrown() {
        testNewTaskDto.setStatus("low");

        assertThatThrownBy(() -> taskService.createTask(testNewTaskDto))
                .isInstanceOf(InvalidTaskStatusException.class)
                .hasMessageContaining("Status must be in high register and " +
                        "can be in variants: IN_PROCESS, AWAITING, COMPLETE");
    }

    @Test
    public void createTask_Optional_ReturnNullFromDao_ExceptionThrown() {
        Mockito.when(taskDao.createTask(testNewTaskDto))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> taskService.createTask(testNewTaskDto))
                .isInstanceOf(CreatingTaskIsFailedException.class)
                .hasMessageContaining("Creating task is failed");
    }

    @Test
    public void getTask_ReturnTaskDto() {
        Mockito.when(taskDao.getTask(1L))
                .thenReturn(Optional.of(testTaskDto));

        assertThat(testTaskDto).isEqualTo(taskService.getTask(1L));
    }

    @Test
    public void getTask_ThrownException() {
        Mockito.when(taskDao.getTask(1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> taskService.getTask(1L))
                .isInstanceOf(TaskNotFoundException.class)
                .hasMessageContaining("Task not found by id" + 1L);
    }

    @Test
    public void getTasks_ReturnListTaskDto() {
        List<TaskDto> list = new ArrayList<>();
        list.add(testTaskDto);
        ListTaskDto listResponse = new ListTaskDto(list);

        Mockito.when(taskDao.getTasks())
                .thenReturn(Optional.of(list));

        assertThat(listResponse).isEqualTo(taskService.getTasks());
    }

    @Test
    public void getTasks_ThrownException() {
        Mockito.when(taskDao.getTasks())
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> taskService.getTasks())
                .isInstanceOf(TaskTableIsEmptyException.class)
                .hasMessageContaining("Task table is empty");

    }

    @Test
    public void updateTask_ReturnTaskDto() {

        Mockito.when(taskDao.getTask(1L))
                .thenReturn(Optional.of(testTaskDto));

        assertThat(testTaskDto).isEqualTo(taskService.updateTask(testUpdateTaskDto, 1L));
    }

    @Test
    public void updateTask_ThrownException() {
        Mockito.when(taskDao.getTask(1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> taskService.updateTask(testUpdateTaskDto, 1L))
                .isInstanceOf(TaskNotFoundException.class)
                .hasMessageContaining("Task not found by id" + 1L);
    }

    @Test
    public void deleteTask_ReturnTaskDto() {
        Mockito.when(taskDao.getTask(1L))
                .thenReturn(Optional.of(testTaskDto));

        assertThat(testTaskDto).isEqualTo(taskService.deleteTask(1L));
    }

    @Test
    public void deleteTask_ThrownException() {
        Mockito.when(taskDao.getTask(1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> taskService.deleteTask(1L))
                .isInstanceOf(TaskNotFoundException.class)
                .hasMessageContaining("Task not found by id" + 1L);
    }

    private static NewTaskDto methodReturnTestNewTaskDto() {
        NewTaskDto dto = new NewTaskDto();
        dto.setAuthor(1L);
        dto.setHeader("Scammers");
        dto.setDescription("I'm tired of the scammers");
        dto.setStatus("IN_PROCESS");
        dto.setPriority("LOW");
        return dto;
    }

    private static TaskDto methodReturnTestTaskDto() {
        TaskDto dto = new TaskDto();
        dto.setId(1L);
        dto.setAuthor(1L);
        dto.setHeader("Scammers");
        dto.setDescription("I'm tired of the scammers");
        dto.setStatus("IN_PROCESS");
        dto.setPriority("LOW");
        dto.setExecutor(2L);
        return dto;
    }

    private static UpdateTaskDto methodReturnTestUpdateTaskDto() {
        UpdateTaskDto dto = new UpdateTaskDto();
        dto.setAuthor(1L);
        dto.setHeader("Scammers");
        dto.setDescription("I'm tired of the scammers");
        dto.setStatus("IN_PROCESS");
        dto.setPriority("LOW");
        dto.setExecutor(2L);
        return dto;
    }

}
