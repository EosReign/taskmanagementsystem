package ru.eosreign.taskmanagementsystem.mapper.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import ru.eosreign.taskmanagementsystem.dto.CommentDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentDtoMapper implements RowMapper<CommentDto> {
    @Override
    public CommentDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        CommentDto dto = new CommentDto();
        dto.setId(rs.getLong("id"));
        dto.setAuthorId(rs.getLong("author"));
        dto.setTaskId(rs.getLong("task"));
        dto.setText(rs.getString("text"));
        dto.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime().toLocalDate());
        return dto;
    }
}
