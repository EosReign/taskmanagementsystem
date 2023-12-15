package ru.eosreign.taskmanagementsystem.mapper.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import ru.eosreign.taskmanagementsystem.dto.TaskDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskDtoMapper implements RowMapper<TaskDto> {

    @Override
    public TaskDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        TaskDto dto = new TaskDto();
        dto.setId(rs.getLong("id"));
        dto.setHeader(rs.getString("header"));
        dto.setDescription(rs.getString("description"));
        dto.setStatus(rs.getString("status"));
        dto.setPriority(rs.getString("priority"));
        dto.setAuthor(rs.getLong("author"));
        dto.setExecutor(rs.getLong("executor"));
        return dto;
    }
}
