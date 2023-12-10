package ru.eosreign.taskmanagementsystem.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.eosreign.taskmanagementsystem.entity.Authority;
import ru.eosreign.taskmanagementsystem.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CustomerRowMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getLong("id"));
        customer.setFio(rs.getString("fio"));
        customer.setEmail(rs.getString("email"));
        customer.setPassword(rs.getString("password"));
        customer.setAuthority(new Authority(rs.getLong("id"), "User"));
        customer.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime().toLocalDate());
        return customer;
    }
}
