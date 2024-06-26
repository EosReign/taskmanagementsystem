package ru.eosreign.taskmanagementsystem.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import ru.eosreign.taskmanagementsystem.dto.NewCustomerDto;
import ru.eosreign.taskmanagementsystem.dto.CustomerDto;
import ru.eosreign.taskmanagementsystem.dto.UpdateCustomerDto;
import ru.eosreign.taskmanagementsystem.entity.Authority;
import ru.eosreign.taskmanagementsystem.entity.Customer;
import ru.eosreign.taskmanagementsystem.exception.CustomerNotFoundException;
import ru.eosreign.taskmanagementsystem.mapper.rowmapper.CustomerDtoMapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerDao {

    private final NamedParameterJdbcTemplate template;

    public CustomerDao(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public Optional<Long> createCustomer(NewCustomerDto dto, Long authorityId) {
        String sql = "INSERT INTO customer (fio, email, password, authority, created_at) " +
                "VALUES (:fio, :email, :password, :authority, :created_at) " +
                "RETURNING ID";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("fio", dto.getFio())
                .addValue("email", dto.getEmail())
                .addValue("password", dto.getPassword())
                .addValue("authority", authorityId)
                .addValue("created_at", Timestamp.valueOf(LocalDateTime.now()));

        return Optional.ofNullable(
                        template.queryForObject(sql, parameterSource, Long.class));
    }

    public Optional<CustomerDto> getCustomer(Long id) throws CustomerNotFoundException {
        String sql = "SELECT * FROM customer WHERE customer.id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);

        return Optional.ofNullable(
                        template.queryForObject(sql, parameterSource, new CustomerDtoMapper()));
    }

    public Optional<List<CustomerDto>> getCustomers() throws CustomerNotFoundException {
        String sql = "SELECT * FROM customer LIMIT 20";
        return Optional.ofNullable(
                        template.query(sql, new CustomerDtoMapper()));
    }

    public void updateCustomer(UpdateCustomerDto dto, Long id) {
        String sql = "UPDATE customer " +
                "SET fio = :fio, " +
                "email = :email " +
                "WHERE id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("fio", dto.getFio())
                .addValue("phone", dto.getEmail());
        template.update(sql, parameterSource);
    }

    public void deleteCustomer(Long id) {
        String sql = "DELETE FROM customer WHERE id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        template.update(sql, parameterSource);
    }

    public Optional<Customer> getCustomerByEmail(String email) throws UsernameNotFoundException {
        String sql = "SELECT * FROM customer WHERE customer.email = :email";
        SqlParameterSource parameterSource = new MapSqlParameterSource("email", email);

        return Optional.ofNullable(
                        template.queryForObject(sql, parameterSource, (rs, rowNum) -> {
                            Customer customer = new Customer();
                            Authority authority = new Authority();

                            customer.setId(rs.getLong("id"));
                            customer.setFio(rs.getString("fio"));
                            customer.setPassword(rs.getString("password"));

                            authority.setId(rs.getLong("authority"));
                            customer.setAuthority(authority);
                            customer.setEmail(rs.getString("email"));
                            customer.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime().toLocalDate());
                            return customer;
                        }));

    }
}
