package ru.eosreign.taskmanagementsystem.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.eosreign.taskmanagementsystem.dto.TaskDto;
import ru.eosreign.taskmanagementsystem.exception.EmptyTaskTableException;
import ru.eosreign.taskmanagementsystem.exception.TaskNotFoundException;
import ru.eosreign.taskmanagementsystem.mapper.TaskDtoMapper;

import java.util.List;
import java.util.Optional;

@Repository
public class TaskDao {
    private final NamedParameterJdbcTemplate template;

    public TaskDao(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public Long createTask(TaskDto dto) {
        String sql = "INSERT INTO task (header, description, status, priority, author) " +
                "VALUES (:header, :description, :status, :priority, :author) " +
                "RETURNING ID";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("header", dto.getHeader())
                .addValue("description", dto.getDescription())
                .addValue("priority", dto.getStatus())
                .addValue("priority", dto.getPriority())
                .addValue("author", dto.getAuthor());

        return Optional.ofNullable(
                        template.queryForObject(sql, parameterSource, Long.class))
                .orElseThrow(() -> new RuntimeException("I dont have any idea what's happen."));
    }

    public TaskDto getTask(Long id) throws TaskNotFoundException {
        String sql = "SELECT * FROM task WHERE task.id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);

        return Optional.ofNullable(
                        template.queryForObject(sql, parameterSource, new TaskDtoMapper()))
                .orElseThrow(() -> new TaskNotFoundException(String.format("Task id-%d not found", id)));
    }

    public List<TaskDto> getTasks() throws TaskNotFoundException {
        String sql = "SELECT * FROM task LIMIT 20";
        return Optional.ofNullable(
                        template.query(sql, new TaskDtoMapper()))
                .orElseThrow(() -> new EmptyTaskTableException("table is empty"));
    }

    public void updateTask(TaskDto dto, Long id) {
        String sql = "UPDATE task SET " +
                "header = :header, " +
                "description = :description, " +
                "status = :status, " +
                "priority = :priority, " +
                "author = :author WHERE id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("header", dto.getHeader())
                .addValue("description", dto.getDescription())
                .addValue("priority", dto.getStatus())
                .addValue("priority", dto.getPriority())
                .addValue("author", dto.getAuthor());
        template.update(sql, parameterSource);
    }

    public void deleteTask(Long id) {
        String sql = "DELETE FROM task WHERE id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        template.update(sql, parameterSource);
    }
}
