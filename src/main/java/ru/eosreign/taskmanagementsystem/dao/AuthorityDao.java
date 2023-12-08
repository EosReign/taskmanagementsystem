package ru.eosreign.taskmanagementsystem.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import ru.eosreign.taskmanagementsystem.exception.AuthorityNotFoundException;

import java.util.Optional;

@Repository
public class AuthorityDao {

    private final NamedParameterJdbcTemplate template;

    public AuthorityDao(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public Long createAuthority(String role) {
        String sql = "INSERT INTO authority (role) " +
                "VALUES (:role) " +
                "RETURNING ID";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("role", role);

        return Optional.ofNullable(
                        template.queryForObject(sql, parameterSource, Long.class))
                .orElseThrow(() -> new RuntimeException("I dont have any idea what's happen."));
    }

    public Long getAuthority(Long id) throws AuthorityNotFoundException {
        String sql = "SELECT authority.id FROM authority WHERE authority.id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);

        return Optional.ofNullable(
                        template.queryForObject(sql, parameterSource, Long.class))
                .orElseThrow(() -> new AuthorityNotFoundException(String.format("Authority id-%d not found", id)));
    }

    public void updateAuthority(String role, Long id) {
        String sql = "UPDATE authority SET role = :role WHERE id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("role", role);
        template.update(sql, parameterSource);
    }

    public void deleteAuthority(Long id) {
        String sql = "DELETE FROM authority WHERE id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        template.update(sql, parameterSource);
    }


}
