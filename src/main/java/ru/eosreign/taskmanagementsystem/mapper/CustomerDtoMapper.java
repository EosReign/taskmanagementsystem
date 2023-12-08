package ru.eosreign.taskmanagementsystem.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.eosreign.taskmanagementsystem.dto.CustomerDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDtoMapper implements RowMapper<CustomerDto> {
    @Override
    public CustomerDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        CustomerDto customer = new CustomerDto();
        customer.setFio(rs.getString("fio"));
        customer.setEmail(rs.getString("email"));
        return customer;
    }
}
