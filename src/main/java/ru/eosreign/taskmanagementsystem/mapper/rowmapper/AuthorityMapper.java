package ru.eosreign.taskmanagementsystem.mapper.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import ru.eosreign.taskmanagementsystem.entity.Authority;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorityMapper implements RowMapper<Authority> {
    @Override
    public Authority mapRow(ResultSet rs, int rowNum) throws SQLException {
        Authority authority = new Authority();
        authority.setId(rs.getLong("id"));
        authority.setRole(rs.getString("role"));
        return authority;
    }
}
