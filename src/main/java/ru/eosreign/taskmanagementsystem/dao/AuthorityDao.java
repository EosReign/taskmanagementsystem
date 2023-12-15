package ru.eosreign.taskmanagementsystem.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import ru.eosreign.taskmanagementsystem.entity.Authority;
import ru.eosreign.taskmanagementsystem.exception.AuthorityNotFoundException;
import ru.eosreign.taskmanagementsystem.mapper.rowmapper.AuthorityMapper;

import java.util.Optional;

@Repository
public class AuthorityDao {

    private final NamedParameterJdbcTemplate template;

    public AuthorityDao(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public Optional<Long> createAuthority(String role) {
        String sql = "INSERT INTO authority (role) " +
                "VALUES (:role) " +
                "RETURNING ID";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("role", role);

        return Optional.ofNullable(
                template.queryForObject(sql, parameterSource, Long.class));
    }

    public Optional<Authority> getAuthority(Long id) throws AuthorityNotFoundException {
        String sql = "SELECT authority.id FROM authority WHERE authority.id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);

        return Optional.ofNullable(
                template.queryForObject(sql, parameterSource, new AuthorityMapper())
        );

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
