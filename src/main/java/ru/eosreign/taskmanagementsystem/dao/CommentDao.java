package ru.eosreign.taskmanagementsystem.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.eosreign.taskmanagementsystem.dto.CommentDto;
import ru.eosreign.taskmanagementsystem.dto.NewCommentDto;
import ru.eosreign.taskmanagementsystem.exception.AuthorityNotFoundException;
import ru.eosreign.taskmanagementsystem.exception.CommentNotFoundException;
import ru.eosreign.taskmanagementsystem.mapper.CommentDtoMapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//TODO Вынести Optional обработку из dao
@Repository
public class CommentDao {
    private final NamedParameterJdbcTemplate template;

    public CommentDao(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public Long createComment(NewCommentDto dto) {
        String sql = "INSERT INTO comment (author, task, text, created_at) " +
                "VALUES (:author, :task, :text, :created_at) " +
                "RETURNING ID";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("author", dto.getAuthorId())
                .addValue("task", dto.getTaskId())
                .addValue("text", dto.getText())
                .addValue("created_at", Timestamp.valueOf(LocalDateTime.now()));

        return Optional.ofNullable(
                        template.queryForObject(sql, parameterSource, Long.class))
                .orElseThrow(() -> new RuntimeException("I dont have any idea what's happen."));
    }

    public CommentDto getComment(Long id) throws CommentNotFoundException {
        String sql = "SELECT comment.id FROM comment WHERE comment.id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);

        return Optional.ofNullable(
                        template.queryForObject(sql, parameterSource, new CommentDtoMapper()))
                .orElseThrow(() -> new AuthorityNotFoundException(String.format("Authority id-%d not found", id)));
    }

    public void updateComment(String text, Long id) {
        String sql = "UPDATE comment SET text = :text WHERE id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("text", text);
        template.update(sql, parameterSource);
    }

    public void deleteComment(Long id) {
        String sql = "DELETE FROM comment WHERE id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        template.update(sql, parameterSource);
    }

    public List<CommentDto> getComments() {
        String sql = "SELECT * FROM comment LIMIT 30";

        return Optional.ofNullable(
                        template.query(sql, new CommentDtoMapper()))
                .orElseThrow(() -> new AuthorityNotFoundException(String.format("Comments not found")));
    }
}
