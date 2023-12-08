package ru.eosreign.taskmanagementsystem.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.eosreign.taskmanagementsystem.dto.TaskDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskDtoMapper implements RowMapper<TaskDto> {

    @Override
    public TaskDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        TaskDto dto = new TaskDto();
        dto.setHeader(rs.getString("fio"));
        dto.setDescription(rs.getString("email"));
        dto.setStatus(rs.getString("status"));
        dto.setPriority(rs.getString("priority"));
        dto.setAuthor(rs.getLong("author"));
        return dto;
    }
}
